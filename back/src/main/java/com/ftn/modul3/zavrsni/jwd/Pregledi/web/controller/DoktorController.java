package com.ftn.modul3.zavrsni.jwd.Pregledi.web.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.DoktorService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PacijentService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.DoktorToDoktorDto;
import com.ftn.modul3.zavrsni.jwd.Pregledi.support.PacijentToPacijentDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.DoktorDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;



@RestController
@RequestMapping(value = "/api/doktori")
public class DoktorController {
	
	
	
	@Autowired
	private DoktorService doktorService;
	@Autowired
	private PacijentService pacijentService;
	
	@Autowired
	private PacijentToPacijentDTO toDtoPac;
	@Autowired
	private DoktorToDoktorDto toDto;
	
	
	
	@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<DoktorDTO>> getAll(){
		
		List<Doktor> doktr= doktorService.findAll();
		
		
	        
	   return new ResponseEntity<>(toDto.convert(doktr),HttpStatus.OK);

	}
	
    //@PreAuthorize("hasAnyRole('ROLE_KORISNIK', 'ROLE_ADMIN')")
    @GetMapping("/{id}/pacijenti")
    public ResponseEntity<List<PacijentDTO>> findByFilmId(@PathVariable @Positive(message = "Id must be positive.")  Long id){
        List<Pacijent> pacijenti = pacijentService.findByDoktorId(id);

        return new ResponseEntity<>(toDtoPac.convert(pacijenti), HttpStatus.OK);
    }

	
	

}
