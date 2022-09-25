package br.com.alura.school.section.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.section.request.NewSectionRequest;
import br.com.alura.school.section.response.SectionResponse;
import br.com.alura.school.section.service.SectionService;

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
	
	@GetMapping
	ResponseEntity<List<SectionResponse>> allSections() {
		return ResponseEntity.ok(sectionService.allSections());
	}

	
	@GetMapping("/{sectionCode}")
	ResponseEntity<SectionResponse> sectionByCode(@PathVariable("sectionCode") String sectionCode) {
		return ResponseEntity.ok(sectionService.sectionByCode(sectionCode));
	}
}
