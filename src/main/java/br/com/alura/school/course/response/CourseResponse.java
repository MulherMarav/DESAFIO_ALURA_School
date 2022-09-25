package br.com.alura.school.course.response;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.section.model.Section;

public class CourseResponse {

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final String shortDescription;
    
    @JsonProperty
	private List<Section> sections;	

    public CourseResponse(Course course) {
        this.code = course.getCode();
        this.name = course.getName();
        this.shortDescription = Optional.of(course.getDescription()).map(this::abbreviateDescription).orElse("");
        this.sections = course.getSections();
    }

    private String abbreviateDescription(String description) {
        if (description.length() <= 13) return description;
        return description.substring(0, 10) + "...";
    }
}
