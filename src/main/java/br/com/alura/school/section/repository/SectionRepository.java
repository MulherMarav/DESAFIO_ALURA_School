package br.com.alura.school.section.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.section.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
	
	Optional<Section> findByCode(String code);

}
