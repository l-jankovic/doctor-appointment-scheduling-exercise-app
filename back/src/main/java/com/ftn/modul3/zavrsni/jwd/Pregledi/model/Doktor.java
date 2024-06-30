package com.ftn.modul3.zavrsni.jwd.Pregledi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.KorisnickaUloga;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;


@Entity
@Data

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Doktor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@Column(nullable = false)
    private String ime;
	
	@Column(nullable = false)
    private String prezime;
	
	@Column(nullable = false)
    private String specijalizacija;
	@OneToMany(mappedBy = "doktor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Pacijent> pacijenti= new ArrayList<Pacijent>();
	@Override
	public String toString() {
		return "Doktor []";
	}	
	
	
	
	
	
}
