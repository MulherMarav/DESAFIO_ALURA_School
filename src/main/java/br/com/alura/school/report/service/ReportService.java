package br.com.alura.school.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.enroll.model.Enroll;
import br.com.alura.school.enroll.repository.EnrollRepository;
import br.com.alura.school.report.response.SectionByVideosResponse;

@Service
public class ReportService {

	private final EnrollRepository enrollRepository;

	public ReportService(EnrollRepository enrollRepository) {
		this.enrollRepository = enrollRepository;
	}

	public List<SectionByVideosResponse> allSectionByVideos() {

		List<Enroll> enrollment = enrollRepository.findAll();

		List<Course> courses = enrollment.stream().map(e -> e.getCourse()).collect(Collectors.toList());

		List<SectionByVideosResponse> sectionsWithEnroll = new ArrayList<>();
		
		courses.forEach(c -> c.getSections().forEach(s -> sectionsWithEnroll.add(new SectionByVideosResponse(s, c))));

		return sectionsWithEnroll;
	}
}
