package br.com.alura.school.course.service;

import static java.lang.String.format;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.repository.SectionRepository;
import br.com.alura.school.course.request.NewSectionRequest;
import br.com.alura.school.course.service.validation.CourseValidation;
import br.com.alura.school.user.model.User;
import br.com.alura.school.user.service.validation.InstructorValidation;
import br.com.alura.school.user.service.validation.UserValidation;

@Service
public class SectionService {

	private final CourseValidation courseValidation;

	private final UserValidation userValidation;
	
	private final InstructorValidation instructorValidation;
		
	private final SectionRepository sectionRepository;
	

	public SectionService(CourseValidation courseValidation, UserValidation userValidation,
			InstructorValidation instructorValidation, SectionRepository sectionRepository) {
		this.courseValidation = courseValidation;
		this.userValidation = userValidation;
		this.instructorValidation = instructorValidation;
		this.sectionRepository = sectionRepository;
	}

	public URI newSection(@Valid NewSectionRequest newSectionRequest, String courseCode) {
		Course course = courseValidation.validateFindByCode(courseCode);
		
		String authorUsername = newSectionRequest.getAuthorUsername();
		
		User user = userValidation.validateUserByUsername(authorUsername);
		
		instructorValidation.isAnInstructor(user);
		
		sectionRepository.save(newSectionRequest.toEntity(user, course));

		URI location = URI.create(format("/courses/%s/sections/%s", courseCode, newSectionRequest.getCode()));

		return location;
	}
}
