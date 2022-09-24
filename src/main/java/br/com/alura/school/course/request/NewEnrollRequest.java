package br.com.alura.school.course.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.model.Enroll;
import br.com.alura.school.user.model.User;

public class NewEnrollRequest {
	
	@Size(max = 10)
	@NotBlank
	@JsonProperty
	private final String username;
	
	public NewEnrollRequest(@Size(max = 10) @NotBlank String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public Enroll toEntity(User user, Course course) {
		return new Enroll(user, course);
	}
}
