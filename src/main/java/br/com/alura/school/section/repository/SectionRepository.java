package br.com.alura.school.section.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.section.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
