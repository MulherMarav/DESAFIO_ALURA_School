package br.com.alura.school.section.service.validation.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.section.model.Section;
import br.com.alura.school.section.model.Video;
import br.com.alura.school.section.service.validation.VideoValidation;

@Component
public class VideoValidationImpl implements VideoValidation {

	@Override
	public void validateVideoIsPresent(Section section, Video video) {

		Optional<Video> duplicatedVideo = section.getVideos().stream()
				.filter(v -> v.getVideo().equalsIgnoreCase(video.getVideo())).findFirst();

		if (duplicatedVideo.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					String.format("Video has already been saved in this section %s", section.getCode()));
	}
}
