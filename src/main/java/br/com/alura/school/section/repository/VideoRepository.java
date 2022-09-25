package br.com.alura.school.section.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.section.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
	
}
