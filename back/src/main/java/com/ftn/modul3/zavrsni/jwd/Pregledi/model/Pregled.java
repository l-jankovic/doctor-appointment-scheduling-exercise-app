package com.ftn.modul3.zavrsni.jwd.Pregledi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.Check;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pregled {
	
	@Id
	@EqualsAndHashCode.Include
	@Setter(value = AccessLevel.PUBLIC)
	private Long pacijentId; // Ključ od pacijenta
	    
	@OneToOne
	@MapsId
	@JoinColumn(name = "pacijentId")
	private Pacijent pacijent;
	
	
	@Column
	private LocalDateTime pocetakPregleda;
	
	@Column
	private LocalDateTime krajPregleda;
	
	@Transient
	private int trajanjePregleda;
	
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	private Doktor doktor;
	


	@Column
	private String simptomi;
	
	

	
	
	
	
	

}
