package br.com.alura.school.section.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@Size(max = 30)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String code;

	@Size(max = 40)
	@NotBlank
	@Column(nullable = false, unique = true)
	private String title;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "author_id", nullable = false, unique = true)
	private User author;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Video> videos = new ArrayList<>();

	@Deprecated
	public Section() {
	}

	public Section(@Size(max = 20) @NotBlank String code, @Size(max = 30) @NotBlank String title,
			@NotNull User author) {
		this.code = code;
		this.title = title;
		this.author = author;
	}

	public void addVideo(Video video) {
		this.videos.add(video);
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

	public List<Video> getVideos() {
		return videos;
	}
}
