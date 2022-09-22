package br.com.alura.school.section.service;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.course.service.validation.CourseValidation;
import br.com.alura.school.section.request.NewSectionRequest;
import br.com.alura.school.user.model.UserRole;
import br.com.alura.school.user.service.validation.UserValidation;

@Service
public class SectionService {

	private final CourseValidation courseValidation;

	private final UserValidation userValidation;

	public SectionService(CourseValidation courseValidation, UserValidation userValidation) {
		this.courseValidation = courseValidation;
		this.userValidation = userValidation;
	}

	public URI newSection(@Valid NewSectionRequest newSectionRequest, String courseCode) {
		Course course = courseValidation.findByCode(courseCode);
		
		String authorUsername = newSectionRequest.getAuthorUsername();
		
		User user = userValidation.userByUsername(authorUsername);
		
		if(user.getRole() != UserRole.INSTRUCTOR)
			new throw ResponseStatusException(BAD_REQUEST, format("Author %s must be an instructor", authorUsername));

		return null;
	}

}
