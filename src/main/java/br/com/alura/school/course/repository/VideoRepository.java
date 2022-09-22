package br.com.alura.school.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.model.Section;
import br.com.alura.school.course.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	
	Optional<Video> findBySection(Section section);
}
