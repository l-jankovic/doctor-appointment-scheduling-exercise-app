package com.ftn.modul3.zavrsni.jwd.Pregledi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Doktor;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.DoktorDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;


@Component
public class DoktorToDoktorDto implements Converter<Doktor,DoktorDTO> {

	@Override
	public DoktorDTO convert(Doktor source) {
		DoktorDTO dto= new DoktorDTO();
		
		
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setPrezime(source.getPrezime());
		dto.setSpecijalizacija(source.getPrezime());
		dto.setSpecijalizacija(source.getSpecijalizacija());
		
		return dto;
	}
	
	
	
	  public List<DoktorDTO> convert(List<Doktor> docs){
	        List<DoktorDTO> docDTOS = new ArrayList<>();

	        for(Doktor d : docs) {
	        	DoktorDTO dto = convert(d);
	            docDTOS.add(dto);
	        }

	        return docDTOS;
	    }

}
