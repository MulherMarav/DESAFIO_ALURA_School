package br.com.alura.school.course.service.validation.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.repository.CourseRepository;
import br.com.alura.school.course.service.validation.CourseValidation;

@Component
public class CourseValidationImpl implements CourseValidation {
	
	private final CourseRepository courseRepository;

	public CourseValidationImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public Course validateFindByCode(String code) {
		Course course = courseRepository.findByCode(code).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Course with code %s not found", code)));
		return course;
	}
}
