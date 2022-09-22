package br.com.alura.school.course.service.validation;

import br.com.alura.school.course.model.Section;

public interface SectionValidation {

	Section validateFindByCode(String code);
}
