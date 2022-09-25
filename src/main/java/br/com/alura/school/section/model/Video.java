package br.com.alura.school.section.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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
	
	@Deprecated
	public Video(){}
	
	public Video(@Size(max = 50) @NotBlank String video) {
		this.video = video;
	}

	public String getVideo() {
		return video;
	}
}
