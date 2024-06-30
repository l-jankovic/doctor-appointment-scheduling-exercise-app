package com.ftn.modul3.zavrsni.jwd.Pregledi.web.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PacijentService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.PacijentDtoToPacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.PacijentToPacijentDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;

@RestController
@RequestMapping(value = "/api/pacijenti", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacijentController {

	@Autowired
	private PacijentService pacijentService;

	@Autowired
	private PacijentDtoToPacijent toPacijent;

	@Autowired
	private PacijentToPacijentDTO toDto;
	


	// @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacijentDTO> create(@Valid @RequestBody PacijentDTO pacDto) {
		System.out.println("Id doktora model" +pacDto.getLBO());

		Pacijent p = toPacijent.convert(pacDto);
		System.out.println("Id doktora model" +p.getDoktor().getIme());
		System.out.println("datum pacijental" +p.getDatumRodjenja());
		if (p.getDoktor() == null) {
			System.out.println("USAO OVDE");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("USAO OVDE");
		Pacijent savedP = pacijentService.save(p);
		System.out.println("SNIMLJEN PACIJENT" + savedP.getPol());
		return new ResponseEntity<>(toDto.convert(savedP), HttpStatus.CREATED);
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacijentDTO> update(@PathVariable Long id, @Valid @RequestBody PacijentDTO pacDto) {

		if (!id.equals(pacDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Pacijent p = toPacijent.convert(pacDto);

		if (p.getDoktor() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Pacijent savedP = pacijentService.update(p);

		return new ResponseEntity<>(toDto.convert(savedP), HttpStatus.CREATED);
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Pacijent obrisanP = pacijentService.delete(id);

		if (obrisanP != null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<PacijentDTO> getOne(@PathVariable Long id) {
		Pacijent p = pacijentService.findOne(id);

		if (p != null) {
			return new ResponseEntity<>(toDto.convert(p), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PermitAll
	@GetMapping
	public ResponseEntity<List<PacijentDTO>> getAll(@RequestParam(required = false) Long doktorId,
			@RequestParam(required = false) String ime, @RequestParam(required = false) String prezime,
			@RequestParam(required = false) String lbo,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

		Page<Pacijent> page = pacijentService.find(doktorId, ime, prezime, lbo, pageNo);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toDto.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@PermitAll
	@GetMapping(value = "/polovi")
	public ResponseEntity<List<Pol>> getAll() {


        return ResponseEntity.ok(pacijentService.getAllPolovi());

	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
