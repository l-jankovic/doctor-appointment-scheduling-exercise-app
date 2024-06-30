package com.ftn.modul3.zavrsni.jwd.Pregledi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;

@Repository
public interface DoktorRepo extends JpaRepository<Doktor, Long> {

	

}
