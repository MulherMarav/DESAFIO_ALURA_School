package br.com.alura.school.course.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.course.request.NewSectionRequest;
import br.com.alura.school.course.service.SectionService;

@RestController
@RequestMapping("/courses/{code}/sections")
public class SectionController {

	private final SectionService sectionService;

	public SectionController(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@PostMapping
	ResponseEntity<Void> newSection(@RequestBody @Valid NewSectionRequest newSectionRequest,
			@PathVariable("code") String courseCode) {
		return ResponseEntity.created(sectionService.newSection(newSectionRequest, courseCode)).build();
	}
}
