package com.ftn.modul3.zavrsni.jwd.Pregledi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.repository.PregledRepo;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PregledService;

@Service
public class JpaPregledService implements PregledService {
	
	@Autowired
	private PregledRepo repo;

	@Override
	public List<Pregled> getAll() {
		return repo.findAll();
	}

	@Override
	public Pregled save(Pregled pregled) {
		
		Doktor d= repo.findDoktorAndPregled(pregled.getDoktor().getId(), pregled.getPocetakPregleda());
		if(d==null) {
			
		LocalDateTime kraj= pregled.getPocetakPregleda().plusMinutes(pregled.getTrajanjePregleda());
		pregled.setKrajPregleda(kraj);
		return repo.save(pregled);
		}else {
			throw new IllegalArgumentException("Lekar je zauzet u tom temrinu");
		}
	}

	@Override
	public Pregled delete(Long id) {
		Pregled pregled = repo.findById(id).orElseGet(null);
		if(pregled != null) 
			
			if(LocalDateTime.now().isBefore(pregled.getPocetakPregleda().minusHours(24))){
			repo.delete(pregled);
			return pregled;
			
		}else {
		 	throw new IllegalArgumentException("Prekasno za otkazivanje");
		}
		return null;
	}

	@Override
	public Pregled getOne(Long id) {
		System.out.println("dobijeni id" + id);
		Pregled p=repo.findOneByPacijentId(id);
		
		if(p!=null) {
			
		System.out.println(p.getPacijent().getIme()+ "");
		return p;
		}
		return null;
	}

	@Override
	public Pregled deleteForAdmin(Long id) {
		Pregled pregled = repo.findById(id).orElseGet(null);
		if(pregled != null) {
			
			repo.delete(pregled);
			return pregled;
		}else {
			throw new IllegalArgumentException("Pacijent nije pronadjen");
		}
	}
	
	
	

	
	
}
