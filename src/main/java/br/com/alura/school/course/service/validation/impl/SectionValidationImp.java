package br.com.alura.school.course.service.validation.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.course.model.Section;
import br.com.alura.school.course.repository.SectionRepository;
import br.com.alura.school.course.service.validation.SectionValidation;

@Component
public class SectionValidationImp implements SectionValidation {
	
	private final SectionRepository sectionRepository;
	
	public SectionValidationImp(SectionRepository sectionRepository) {
		this.sectionRepository = sectionRepository;
	}

	@Override
	public Section validateFindByCode(String code) {
		Section section = sectionRepository.findByCode(code).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Section with code %s not found", code)));
		return section;
	}

}
