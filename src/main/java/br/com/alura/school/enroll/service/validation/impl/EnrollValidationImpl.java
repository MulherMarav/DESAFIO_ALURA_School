package br.com.alura.school.enroll.service.validation.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.enroll.model.Enroll;
import br.com.alura.school.enroll.repository.EnrollRepository;
import br.com.alura.school.enroll.service.validation.EnrollValidation;
import br.com.alura.school.user.model.User;

@Component
public class EnrollValidationImpl implements EnrollValidation {
	
	private final EnrollRepository enrollRepository;

	public EnrollValidationImpl(EnrollRepository enrollRepository) {
		this.enrollRepository = enrollRepository;
	}

	@Override
	public void validateEnrollByUserAndCourse(User user, Course course) {
		Optional<Enroll> enroll = enrollRepository.findByUserAndCourse(user, course);

		if (enroll.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("User %s is already enrolled in this course %s", user.getUsername(), course.getCode()));
		
	}
}
