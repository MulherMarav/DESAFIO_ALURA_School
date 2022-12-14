package br.com.alura.school.section.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.service.validation.CourseValidation;
import br.com.alura.school.section.model.Section;
import br.com.alura.school.section.model.Video;
import br.com.alura.school.section.repository.SectionRepository;
import br.com.alura.school.section.request.NewVideoRequest;
import br.com.alura.school.section.service.validation.SectionValidation;
import br.com.alura.school.section.service.validation.VideoValidation;

@Service
public class VideoService {
	
	private final CourseValidation courseValidation;
	
	private final SectionValidation sectionValidation;
	
	private final VideoValidation videoValidation;
	
	private final SectionRepository sectionRepository;


	public VideoService(CourseValidation courseValidation, SectionValidation sectionValidation,
			VideoValidation videoValidation, SectionRepository sectionRepository) {
		this.courseValidation = courseValidation;
		this.sectionValidation = sectionValidation;
		this.videoValidation = videoValidation;
		this.sectionRepository = sectionRepository;
	}

	public void newVideo(@Valid NewVideoRequest newVideoRequest, String sectionCode, String courseCode) {

		courseValidation.validateFindByCode(courseCode);
		
		Section section = sectionValidation.validateFindByCode(sectionCode);
		
		Video video = newVideoRequest.toEntity();
		
		videoValidation.validateVideoIsPresent(section, video);
		
		section.addVideo(video);
		
		sectionRepository.save(section);
	}
}
