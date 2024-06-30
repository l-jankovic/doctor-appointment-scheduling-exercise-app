package com.ftn.modul3.zavrsni.jwd.Pregledi.service;

import java.util.List;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;

public interface PregledService {
	
	List<Pregled> getAll();
	
	Pregled save(Pregled pregled);
	
	Pregled delete(Long id);
	
	Pregled deleteForAdmin(Long id);
	
	Pregled getOne(Long id);

}
