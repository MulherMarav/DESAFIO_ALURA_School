package br.com.alura.school.section.service.validation;

import br.com.alura.school.section.model.Section;
import br.com.alura.school.section.model.Video;

public interface VideoValidation {
	
	void validateVideoIsPresent(Section section, Video video);
}
