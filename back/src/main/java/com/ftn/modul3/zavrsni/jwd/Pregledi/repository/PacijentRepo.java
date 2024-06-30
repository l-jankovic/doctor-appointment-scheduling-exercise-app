package com.ftn.modul3.zavrsni.jwd.Pregledi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;

@Repository
public interface PacijentRepo extends JpaRepository<Pacijent, Long> {
	
	
	
	List<Pacijent> findByDoktorId(Long doktorId);
	
	@Query("SELECT p FROM Pacijent p WHERE  "
			+ "(:doktorId IS NULL OR p.doktor.id=:doktorId) AND"
			+ "(:ime IS NULL OR p.ime LIKE %:ime%) AND"
			+ "(:prezime IS NULL OR p.prezime LIKE %:prezime%) AND"
			+ "(:LBO IS NULL OR p.LBO LIKE %:LBO%)")
	Page<Pacijent> search(@Param("doktorId") Long doktorId, @Param("ime") String ime,
			@Param("prezime") String prezime, @Param("LBO") String LBO, Pageable pageable);

}
