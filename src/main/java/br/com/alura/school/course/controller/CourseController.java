package br.com.alura.school.course.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.course.request.NewCourseRequest;
import br.com.alura.school.course.response.CourseResponse;
import br.com.alura.school.course.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping
	ResponseEntity<List<CourseResponse>> allCourses() {
		return ResponseEntity.ok(courseService.allCourses());
	}

	@GetMapping("/{code}")
	ResponseEntity<CourseResponse> courseByCode(@PathVariable("code") String code) {
		return ResponseEntity.ok(courseService.courseByCode(code));
	}

	@PostMapping
	ResponseEntity<Void> newCourse(@RequestBody @Valid NewCourseRequest newCourseRequest) {
		return ResponseEntity.created(courseService.newCourse(newCourseRequest)).build();
	}
}
