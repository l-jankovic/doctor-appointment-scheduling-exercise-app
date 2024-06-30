package com.ftn.modul3.zavrsni.jwd.Pregledi.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.DoktorService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PregledDTO;

@Component
public class PregledToPregledDto implements Converter<Pregled, PregledDTO>{


	
	@Override
	public PregledDTO convert(Pregled source) {
	PregledDTO dto = new PregledDTO();
	
	dto.setPacijentId(source.getPacijentId());
	dto.setPocetakPregleda(source.getPocetakPregleda());
	dto.setTrajanjePregleda(source.getTrajanjePregleda());
	dto.setDoktorId(source.getDoktor().getId());
	dto.setDoktorIme(source.getDoktor().getIme());
	dto.setSimptomi(source.getSimptomi());
	
	return dto;
	
	
	
	}
	

	
	  public List<PregledDTO> convert(List<Pregled> pregledi){
	        List<PregledDTO> prDTOS = new ArrayList<>();

	        for(Pregled p : pregledi) {
	        	PregledDTO dto = convert(p);
	            prDTOS.add(dto);
	        }

	        return prDTOS;
	    }

	
	
	
	  
}
