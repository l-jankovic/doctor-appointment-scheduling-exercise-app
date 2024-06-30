package com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto;



public class PacijentDTO {
	
	private Long id;
	
	private String ime;
	
	private String prezime;
	
	private String datumRodjenja;
	
	private String mesto;
	
	private String pol;
	
	private String LBO;
	
	private Long doktorId;
	
	private String doktorIme;

	public PacijentDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getLBO() {
		return LBO;
	}

	public void setLBO(String lBO) {
		LBO = lBO;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
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

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	
	
	
	
	
	
	

}
