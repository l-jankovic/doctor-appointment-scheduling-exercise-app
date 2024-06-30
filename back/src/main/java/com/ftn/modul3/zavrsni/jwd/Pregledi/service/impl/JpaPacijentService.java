package com.ftn.modul3.zavrsni.jwd.Pregledi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.repository.PacijentRepo;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PacijentService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PregledService;

@Service
public class JpaPacijentService implements PacijentService {
	
	@Autowired
	private PacijentRepo repo;
	@Autowired
	private PregledService pregledService;
	

	@Override
	public Pacijent findOne(Long id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public Page<Pacijent> findAll(int pageNo) {
		return repo.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public Pacijent save(Pacijent pacijent) {
		System.out.println("SNIMLJEN PACIJENT " + pacijent.getIme());
		return repo.save(pacijent);
	}

	@Override
	public Pacijent update(Pacijent pacijent) {
		return repo.save(pacijent);
	}

	@Override
	public Pacijent delete(Long id) {
		Pacijent pacijent = findOne(id);
		
		if(pacijent != null) {
			try {
			pacijent.getDoktor().getPacijenti().remove(pacijent);
			Pregled pacijentPregledan =pregledService.getOne(id);
			
			if(pacijentPregledan!=null){
				pregledService.deleteForAdmin(id);// deletes appointment first
			}
			repo.delete(pacijent);//then patient
			return pacijent;
			
			}catch (Exception e) {
				 System.err.println("Error while deleting patient: " + e.getMessage());
		         e.printStackTrace();
			}       
		}
		
		return null;
	}

	@Override
	public Page<Pacijent> find(Long doktorId, String ime, String prezime, String LBO, int pageNo) {
		
		if(ime==null) {
			ime="";
		}
		if(prezime==null) {
			prezime="";
		}
		
		if(LBO==null) {
			LBO="";
		}
		return repo.search(doktorId, ime, prezime, LBO, PageRequest.of(pageNo, 2));
	}

	@Override
	public List<Pacijent> findByDoktorId(Long doktorId) {
		return  repo.findByDoktorId(doktorId);
	}

	@Override
	public List<Pol> getAllPolovi() {
		List<Pol> polovi = Arrays.asList(Pol.values());
		
		return polovi;
	}

}
