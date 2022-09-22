package br.com.alura.school.course.service.validation.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.course.model.Section;
import br.com.alura.school.course.model.Video;
import br.com.alura.school.course.repository.VideoRepository;
import br.com.alura.school.course.service.validation.VideoValidation;

@Component
public class VideoValidationImpl implements VideoValidation {

	private final VideoRepository videoRepository;

	public VideoValidationImpl(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	@Override
	public void validateVideoIsPresent(Section section) {
		Optional<Video> video = videoRepository.findBySection(section);

		if (video.isPresent())
			new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("Video has already been saved in this section %s", section.getCode()));
	}
}
