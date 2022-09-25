package br.com.alura.school.section.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.section.model.Video;

public class NewVideoRequest {
	
	@Size(max = 50)
	@NotBlank
	@JsonProperty
	private final String video;

	@JsonCreator
	public NewVideoRequest(@Size(max = 50) @NotBlank String video) {
		this.video = video;
	}

	public String getVideo() {
		return video;
	}
	
	public Video toEntity() {
		return new Video(video);
	}
}
