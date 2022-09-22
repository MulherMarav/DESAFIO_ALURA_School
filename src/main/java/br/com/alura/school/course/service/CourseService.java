package br.com.alura.school.course.service;

import static java.lang.String.format;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.repository.CourseRepository;
import br.com.alura.school.course.request.NewCourseRequest;
import br.com.alura.school.course.response.CourseResponse;
import br.com.alura.school.course.service.validation.CourseValidation;

@Service
public class CourseService {

	private final CourseRepository courseRepository;
	
	private final CourseValidation courseValidation;

	public CourseService(CourseRepository courseRepository, CourseValidation courseValidation) {
		this.courseRepository = courseRepository;
		this.courseValidation = courseValidation;
	}

	public List<CourseResponse> allCourses() {
		List<CourseResponse> courses = courseRepository.findAll().stream().map(c -> new CourseResponse(c))
				.collect(Collectors.toList());
		return courses;
	}

	public CourseResponse courseByCode(String code) {
		Course course = courseValidation.validateFindByCode(code);
		return new CourseResponse(course);
	}

	public URI newCourse(@Valid NewCourseRequest newCourseRequest) {
		courseRepository.save(newCourseRequest.toEntity());
		URI location = URI.create(format("/courses/%s", newCourseRequest.getCode()));
		return location;
	}
}
