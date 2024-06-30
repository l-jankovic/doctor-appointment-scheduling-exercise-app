package com.ftn.modul3.zavrsni.jwd.Pregledi.service;

import java.util.List;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;

public interface DoktorService {
	
	Doktor findOne(Long id);
	
	List<Doktor> findAll();
	
	
	
	

}
