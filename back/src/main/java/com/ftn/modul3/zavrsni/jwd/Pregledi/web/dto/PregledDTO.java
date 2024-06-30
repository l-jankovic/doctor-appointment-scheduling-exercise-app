package com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class PregledDTO {
	
	private Long pacijentId;
	
	private LocalDateTime pocetakPregleda;
	

	private int trajanjePregleda;
	
	private String simptomi;
	
	private Long doktorId;
	
	private String doktorIme;

	public PregledDTO() {
		super();
	}

	public Long getPacijentId() {
		return pacijentId;
	}

	public void setPacijentId(Long pacijentId) {
		this.pacijentId = pacijentId;
	}


	public LocalDateTime getPocetakPregleda() {
		return pocetakPregleda;
	}

	public void setPocetakPregleda(LocalDateTime pocetakPregleda) {
		this.pocetakPregleda = pocetakPregleda;
	}

	public int getTrajanjePregleda() {
		return trajanjePregleda;
	}

	public void setTrajanjePregleda(int trajanjePregleda) {
		this.trajanjePregleda = trajanjePregleda;
	}

	public String getSimptomi() {
		return simptomi;
	}

	public void setSimptomi(String simptomi) {
		this.simptomi = simptomi;
	}

	public Long getDoktorId() {
		return doktorId;
	}

	public void setDoktorId(Long doktorId) {
		this.doktorId = doktorId;
	}

	public String getDoktorIme() {
		return doktorIme;
	}

	public void setDoktorIme(String doktorIme) {
		this.doktorIme = doktorIme;
	}
	
	
	
	

}
