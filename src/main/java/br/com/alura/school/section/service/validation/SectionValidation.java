package br.com.alura.school.section.service.validation;

import br.com.alura.school.section.model.Section;

public interface SectionValidation {

	Section validateFindByCode(String code);
}
