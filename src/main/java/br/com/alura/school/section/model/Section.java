package br.com.alura.school.section.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.school.course.model.Course;

@Entity
public class Section {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Size(max = 20)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String code;

	@Size(max = 30)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String title;

	@Size(max = 10)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String authorUsername;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	@Deprecated
	public Section() {
	}

	public Section(@Size(max = 20) @NotBlank String code, @Size(max = 30) @NotBlank String title,
			@Size(max = 10) @NotBlank String authorUsername) {
		this.code = code;
		this.title = title;
		this.authorUsername = authorUsername;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}
	
	public Course getCourse() {
		return course;
	}
}
