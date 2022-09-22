package br.com.alura.school.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
	Optional<Course> findByCode(String code);
}
