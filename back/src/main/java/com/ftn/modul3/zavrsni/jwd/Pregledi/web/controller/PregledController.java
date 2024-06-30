package com.ftn.modul3.zavrsni.jwd.Pregledi.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PregledService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.PregledDtoToPregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.PregledToPregledDto;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.DoktorDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PregledDTO;


@RestController
@RequestMapping(value = "/api/pregledi",consumes = MediaType.APPLICATION_JSON_VALUE)
public class PregledController {
	
	
	@Autowired
	private PregledService pregledService;
	
	@Autowired
	private PregledDtoToPregled toPregled;
	
	@Autowired
	private PregledToPregledDto toDto;

	

	//@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<PregledDTO>> getAll(){
		
		List<Pregled> pregledi= pregledService.getAll();
		
		
	        
	   return new ResponseEntity<>(toDto.convert(pregledi),HttpStatus.OK);

	}
	
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PregledDTO> create(@Valid @RequestBody PregledDTO pregledDTO) {
		
			System.out.println(pregledDTO.getSimptomi());
		Pregled p = toPregled.convert(pregledDTO);
	
		if (p.getDoktor() == null) {
			System.out.println("USAO OVDE");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("USAO OVDE posle doktora");
		Pregled savedP= pregledService.save(p);
		System.out.println("SNIMLJEN pregled"+savedP.getPacijent());
		return new ResponseEntity<>(toDto.convert(savedP), HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		Pregled obrisanP = pregledService.delete(id);

		if (obrisanP != null) {

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
