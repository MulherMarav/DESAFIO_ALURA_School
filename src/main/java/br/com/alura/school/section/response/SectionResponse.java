package br.com.alura.school.section.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.section.model.Section;
import br.com.alura.school.section.model.Video;
import br.com.alura.school.user.model.User;

public class SectionResponse {
	
    @JsonProperty
	private final String code;

    @JsonProperty
	private final String title;

    @JsonProperty
	private final User author;
	
    @JsonProperty
	private final List<Video> videos;

	public SectionResponse(Section section) {
		this.code = section.getCode();
		this.title = section.getTitle();
		this.author = section.getAuthor();
		this.videos = section.getVideos();
	}	
}
