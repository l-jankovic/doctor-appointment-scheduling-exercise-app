package com.ftn.modul3.zavrsni.jwd.Pregledi.support;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Pregledi.enumeration.Pol;
import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pacijent;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.DoktorService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PacijentService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PacijentDTO;



@Component
public class PacijentDtoToPacijent implements Converter<PacijentDTO, Pacijent> {

	@Autowired
	private PacijentService pacijentService;
	@Autowired
	private DoktorService doktorService;
	
	@Override
	public Pacijent convert(PacijentDTO source) {
		
		Pacijent p = null;
		
		
		if(source.getId()==null) {
			p=new Pacijent();
		}else {
			p=pacijentService.findOne(source.getId());
		}
		
		if(p!=null) {
			p.setDatumRodjenja(getLocalDate(source.getDatumRodjenja()));
			p.setMesto(source.getMesto());
			p.setDoktor(doktorService.findOne(source.getDoktorId()));
			p.setLBO(source.getLBO());
			p.setIme(source.getIme());
			p.setPrezime(source.getPrezime());
			p.setPol(Pol.fromString(source.getPol()));
		
			
		}
		
		return p;
		
	}
	
	
    private LocalDate getLocalDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateTime, formatter);
    }
    
   
	

}
