package br.com.alura.school.course.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Section;
import br.com.alura.school.course.repository.VideoRepository;
import br.com.alura.school.course.request.NewVideoRequest;
import br.com.alura.school.course.service.validation.SectionValidation;
import br.com.alura.school.course.service.validation.VideoValidation;

@Service
public class VideoService {
	
	private final SectionValidation sectionValidation;
	
	private final VideoValidation videoValidation;
	
	private final VideoRepository videoRepository;

	
	public VideoService(SectionValidation sectionValidation, VideoValidation videoValidation,
			VideoRepository videoRepository) {
		this.sectionValidation = sectionValidation;
		this.videoValidation = videoValidation;
		this.videoRepository = videoRepository;
	}

	public void newVideo(@Valid NewVideoRequest newVideoRequest, String sectionCode, String courseCode) {

		Section section = sectionValidation.validateFindByCode(sectionCode);
		
		videoValidation.validateVideoIsPresent(section);
		
		videoRepository.save(newVideoRequest.toEntity(section));
	}
}
