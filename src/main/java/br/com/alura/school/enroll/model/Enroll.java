package br.com.alura.school.enroll.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.user.model.User;

@Entity	
public class Enroll {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	@NotNull
	private LocalDateTime date;
	
	@Deprecated
	public Enroll(){}

	public Enroll(@NotNull User user, @NotNull Course course) {
		this.user = user;
		this.course = course;
		this.date = LocalDateTime.now();
	}
	
	public Course getCourse() {
		return course;
	}
}
