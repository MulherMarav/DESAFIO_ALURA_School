package br.com.alura.school.course.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Section;
import br.com.alura.school.course.model.Video;

public class NewVideoRequest {
	
	@Size(max = 50)
	@NotBlank
	@JsonProperty
	private final String video;

	public NewVideoRequest(@Size(max = 50) @NotBlank String video) {
		this.video = video;
	}

	public String getVideo() {
		return video;
	}
	
	public Video toEntity(Section section) {
		return new Video(video, section);
	}
}
