package com.ftn.modul3.zavrsni.jwd.Pregledi.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftn.modul3.zavrsni.jwd.Pregledi.model.Pregled;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.DoktorService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PacijentService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.service.PregledService;
import com.ftn.modul3.zavrsni.jwd.Pregledi.web.dto.PregledDTO;

@Component
public class PregledDtoToPregled implements Converter<PregledDTO, Pregled> {

	@Autowired
	private DoktorService doktorService;
	@Autowired
	private PacijentService pacijentService;

	@Override
	public Pregled convert(PregledDTO source) {
		Pregled p = null;

		System.out.println("datum " + source.getPocetakPregleda());

		p = new Pregled();
		p.setPacijent(pacijentService.findOne(source.getPacijentId()));

		p.setPocetakPregleda(source.getPocetakPregleda());
		p.setDoktor(doktorService.findOne(source.getDoktorId()));
		p.setSimptomi(source.getSimptomi());
		p.setTrajanjePregleda(source.getTrajanjePregleda());

		System.out.println("KONBVERTOVANO GOVNO " + p.getSimptomi() + "344442342");

		return p;
	}

	
}
