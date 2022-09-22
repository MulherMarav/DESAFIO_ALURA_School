package br.com.alura.school.course.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Size(max = 50)
	@NotBlank
	@Column(nullable = false)
	private String video;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "section_id", nullable = false)
	private Section section;
	
	@Deprecated
	public Video(){}
	
	public Video(@Size(max = 50) @NotBlank String video, @NotNull Section section) {
		this.video = video;
		this.section = section;
	}

	public String getVideo() {
		return video;
	}
	
	public Section getSection() {
		return section;
	}
}
