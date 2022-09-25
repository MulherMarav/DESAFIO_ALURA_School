package br.com.alura.school.report.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.report.response.SectionByVideosResponse;
import br.com.alura.school.report.service.ReportService;

@RestController
@RequestMapping("/sectionByVideosReport")
public class ReportController {
	
	private final ReportService reportService;
	
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping
	ResponseEntity<?> allSectionByVideos() {
		
		List<SectionByVideosResponse> responses = reportService.allSectionByVideos();
		
		if(responses.isEmpty()) 
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(responses);
	}
}
