package br.com.alura.school.course.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.course.request.NewEnrollRequest;
import br.com.alura.school.course.service.EnrollService;

@RestController
@RequestMapping("/courses/{courseCode}/enroll")
public class EnrollController {

	private final EnrollService enrollService;
	
	public EnrollController(EnrollService enrollService) {
		this.enrollService = enrollService;
	}

	@PostMapping
	ResponseEntity<Void> newEnroll(@RequestBody @Valid NewEnrollRequest newEnrollRequest,
			@PathVariable("courseCode") String courseCode) {
		
		enrollService.newEnroll(newEnrollRequest, courseCode);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
