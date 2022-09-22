package br.com.alura.school.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
	
	Optional<Section> findByCode(String code);

}
