package com.ftn.modul3.zavrsni.jwd.Pregledi.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;

@Repository
public interface PregledRepo extends JpaRepository<Pregled, Long>{
	
	Pregled findOneByPacijentId(Long id);
	

@Query("SELECT d FROM Pregled p JOIN Doktor d ON p.doktor.id = d.id WHERE d.id = :doktorId AND p.pocetakPregleda = :vremePregleda")
    Doktor findDoktorAndPregled(@Param("doktorId") Long doktorId, @Param("vremePregleda") LocalDateTime vremePregleda);
}
