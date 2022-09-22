package br.com.alura.school.course.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.support.validation.Unique;

public class NewCourseRequest {

    @Unique(entity = Course.class, field = "code")
    @Size(max=10)
    @NotBlank
    @JsonProperty
    private final String code;

    @Unique(entity = Course.class, field = "name")
    @Size(max=20)
    @NotBlank
    @JsonProperty
    private final String name;

    @JsonProperty
    private final String description;

    public NewCourseRequest(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public Course toEntity() {
        return new Course(code, name, description);
    }
}
