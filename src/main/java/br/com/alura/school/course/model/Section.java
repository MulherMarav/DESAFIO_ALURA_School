package br.com.alura.school.course.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.alura.school.user.model.User;

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

	@ManyToOne
	@NotNull
	@JoinColumn(name = "author_id", nullable = false, unique = true)
	private User author;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	@OneToMany
	private List<Video> videos = new ArrayList<>();	

	@Deprecated
	public Section() {
	}

	public Section(@Size(max = 20) @NotBlank String code, @Size(max = 30) @NotBlank String title, @NotNull User author,
			@NotNull Course course) {
		this.code = code;
		this.title = title;
		this.author = author;
		this.course = course;
	}

	public String getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public User getAuthor() {
		return author;
	}

	public Course getCourse() {
		return course;
	}
	
	public List<Video> getVideos() {
		return videos;
	}
}
