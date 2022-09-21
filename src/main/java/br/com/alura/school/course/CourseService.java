package br.com.alura.school.course;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CourseService {

	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<CourseResponse> allCourses() {
		List<CourseResponse> courses = courseRepository.findAll().stream().map(c -> new CourseResponse(c))
				.collect(Collectors.toList());
		return courses;
	}

	public CourseResponse courseByCode(String code) {
		Course course = courseRepository.findByCode(code).orElseThrow(
				() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));
		return new CourseResponse(course);
	}

	public URI newCourse(@Valid NewCourseRequest newCourseRequest) {
		courseRepository.save(newCourseRequest.toEntity());
		URI location = URI.create(format("/courses/%s", newCourseRequest.getCode()));
		return location;
	}
}
