package br.com.alura.school.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.model.Course;

import java.util.Optional;

interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCode(String code);
}
