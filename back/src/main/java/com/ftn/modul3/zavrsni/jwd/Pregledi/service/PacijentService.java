package com.ftn.modul3.zavrsni.jwd.Pregledi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;


public interface PacijentService {
	
	 Pacijent findOne(Long id);

	 Page<Pacijent> findAll(int pageNo);

	 Pacijent save(Pacijent pacijent);

	 Pacijent update(Pacijent pacijent);

	 Pacijent delete(Long id);

	 Page<Pacijent> find( Long doktorId, String ime, String prezime, String LBO, 
	            int pageNo);

	 List<Pacijent> findByDoktorId(Long doktorId);
	 
	 List<Pol> getAllPolovi();
}
