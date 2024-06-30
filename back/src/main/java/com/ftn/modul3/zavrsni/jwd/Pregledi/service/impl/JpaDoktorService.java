package com.ftn.modul3.zavrsni.jwd.Pregledi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.repository.DoktorRepo;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.DoktorService;

@Service
public class JpaDoktorService implements DoktorService{
	
	@Autowired
	private DoktorRepo repo;

	@Override
	public Doktor findOne(Long id) {
		return repo.findById(id).orElseGet(null);
	}

	@Override
	public List<Doktor> findAll() {
		return repo.findAll();
	}
	
	

}
