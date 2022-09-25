package br.com.alura.school.report.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.section.model.Section;

public class SectionByVideosResponse {
	
    @JsonProperty
    private final String courseName;
    
    @JsonProperty
    private final String sectionTitle;
    
    @JsonProperty
    private final String authorName;
    
    @JsonProperty
    private final Integer totalVideos;

	public SectionByVideosResponse(Section section, Course course) {
		this.courseName = course.getName();
		this.sectionTitle = section.getTitle();
		this.authorName = section.getAuthor().getUsername();
		this.totalVideos = section.getVideos().size();
	}
}
