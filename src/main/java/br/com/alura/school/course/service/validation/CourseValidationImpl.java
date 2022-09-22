package br.com.alura.school.course.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CourseValidationImpl implements CourseValidation {
	
	private final CourseRepository courseRepository;

	public CourseValidationImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course findByCode(String code) {
		Course course = courseRepository.findByCode(code).orElseThrow(
				() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));
		return couse;
	}
}
