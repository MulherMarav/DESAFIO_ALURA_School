package br.com.alura.school.section.service;

import static java.lang.String.format;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.repository.CourseRepository;
import br.com.alura.school.course.service.validation.CourseValidation;
import br.com.alura.school.section.model.Section;
import br.com.alura.school.section.repository.SectionRepository;
import br.com.alura.school.section.request.NewSectionRequest;
import br.com.alura.school.section.response.SectionResponse;
import br.com.alura.school.section.service.validation.SectionValidation;
import br.com.alura.school.user.model.User;
import br.com.alura.school.user.service.validation.InstructorValidation;
import br.com.alura.school.user.service.validation.UserValidation;

@Service
public class SectionService {

	private final CourseValidation courseValidation;

	private final UserValidation userValidation;

	private final InstructorValidation instructorValidation;
	
	private final SectionValidation sectionValidation;

	private final SectionRepository sectionRepository;
	
	private final CourseRepository courseRepository; 
	
	public SectionService(CourseValidation courseValidation, UserValidation userValidation,
			InstructorValidation instructorValidation, SectionValidation sectionValidation,
			SectionRepository sectionRepository, CourseRepository courseRepository) {
		this.courseValidation = courseValidation;
		this.userValidation = userValidation;
		this.instructorValidation = instructorValidation;
		this.sectionValidation = sectionValidation;
		this.sectionRepository = sectionRepository;
		this.courseRepository = courseRepository;
	}

	public URI newSection(@Valid NewSectionRequest newSectionRequest, String courseCode) {
		Course course = courseValidation.validateFindByCode(courseCode);

		String authorUsername = newSectionRequest.getAuthorUsername();

		User user = userValidation.validateUserByUsername(authorUsername);

		instructorValidation.isAnInstructor(user);
		
		Section section = newSectionRequest.toEntity(user);
		
		course.addSection(section);

		courseRepository.save(course);

		URI location = URI.create(format("/courses/%s/sections/%s", courseCode, newSectionRequest.getCode()));

		return location;
	}

	public List<SectionResponse> allSections() {

		List<SectionResponse> responses = sectionRepository.findAll().stream().map(s -> new SectionResponse(s))
				.collect(Collectors.toList());

		return responses;
	}

	public SectionResponse sectionByCode(String sectionCode) {

		Section section = sectionValidation.validateFindByCode(sectionCode);
		
		return new SectionResponse(section);
	}
}
