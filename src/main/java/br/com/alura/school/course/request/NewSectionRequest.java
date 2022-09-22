package br.com.alura.school.course.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.model.Section;
import br.com.alura.school.support.validation.Unique;
import br.com.alura.school.user.model.User;

public class NewSectionRequest {

	@Unique(entity = Section.class, field = "code")
	@Size(max = 20)
	@NotBlank
	@JsonProperty
	private final String code;

	@Size(max = 30, min = 5)
	@NotBlank
	@JsonProperty
	private final String title;

	@Size(max = 10)
	@NotBlank
	@JsonProperty
	private final String authorUsername;

	public NewSectionRequest(@Size(max = 20) @NotBlank String code, @Size(max = 30, min = 5) @NotBlank String title,
			@Size(max = 10) @NotBlank String authorUsername) {
		this.code = code;
		this.title = title;
		this.authorUsername = authorUsername;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}
	
	public String getCode() {
		return code;
	}

	public Section toEntity(User author, Course course) {
		return new Section(code, title, author, course);
	}
}
