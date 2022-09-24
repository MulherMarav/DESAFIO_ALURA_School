package br.com.alura.school.course.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.school.course.request.NewVideoRequest;
import br.com.alura.school.course.service.VideoService;

@RestController
@RequestMapping("/courses/{courseCode}/sections/{sectionCode}")
public class VideoController {

	private final VideoService videoService;

	public VideoController(VideoService videoService) {
		this.videoService = videoService;
	}

	@PostMapping
	ResponseEntity<Void> newVideo(@RequestBody @Valid NewVideoRequest newVideoRequest,
			@PathVariable("courseCode") String courseCode, @PathVariable("sectionCode") String sectionCode) {
		
		videoService.newVideo(newVideoRequest, sectionCode, courseCode);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
