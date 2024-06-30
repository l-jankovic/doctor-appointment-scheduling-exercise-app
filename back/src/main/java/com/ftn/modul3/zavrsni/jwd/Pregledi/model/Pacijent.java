package com.ftn.modul3.zavrsni.jwd.Pregledi.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pacijent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column
	private String  mesto;
	
	
	@Column(nullable = false)
	private LocalDate datumRodjenja;
	
	
	@Enumerated(EnumType.STRING)
	private Pol pol;
	
	@ManyToOne(optional = false)
	private Doktor doktor;
	
	@Column(nullable = false,unique = true)
	private String LBO; //AKO JE NESTO VELIKIM SLOVIMA SALJEMO GA MALIM u bazu

	@Override
	public String toString() {
		return "Pacijent []";
	}



	
	
	
	
	
	
	
	

}
