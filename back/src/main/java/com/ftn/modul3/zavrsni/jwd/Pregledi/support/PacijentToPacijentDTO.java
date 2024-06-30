package com.ftn.modul3.zavrsni.jwd.Pregledi.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Korisnik;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.KorisnikDTO;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;

@Component
public class PacijentToPacijentDTO implements Converter<Pacijent, PacijentDTO> {

	@Override
	public PacijentDTO convert(Pacijent source) {
		PacijentDTO dto= new PacijentDTO();
		System.out.println("KRENUO SAM DA KONVERTJEM       ");
		dto.setId(source.getId());
		dto.setIme(source.getIme());
		dto.setPrezime(source.getPrezime());
		dto.setDoktorId(source.getDoktor().getId());
		dto.setDoktorIme(source.getDoktor().getIme());
		dto.setDatumRodjenja(source.getDatumRodjenja().toString());
		dto.setMesto(source.getMesto());
		dto.setLBO(source.getLBO());
		dto.setPol(source.getPol().toString());
		
		
		return dto;
	}
	
	
	  public List<PacijentDTO> convert(List<Pacijent> pacijents){
	        List<PacijentDTO> pacDTOS = new ArrayList<>();

	        for(Pacijent p : pacijents) {
	        	PacijentDTO dto = convert(p);
	            pacDTOS.add(dto);
	        }

	        return pacDTOS;
	    }

}
