package br.com.alura.school.course.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.school.section.model.Section;

@Entity
class Course {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Size(max = 10)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String code;

	@Size(max = 20)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String name;

	private String description;
	
	@OneToMany
	private List<Section> sections = new ArrayList<>();	

	@Deprecated
	protected Course() {
	}

	Course(String code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	String getCode() {
		return code;
	}

	String getName() {
		return name;
	}

	String getDescription() {
		return description;
	}

	public List<Section> getSections() {
		return sections;
	}
}
