package br.com.alura.school.enroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.enroll.model.Enroll;
import br.com.alura.school.user.model.User;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

	Optional<Enroll> findByUserAndCourse(User user, Course course);

	List<Enroll> findByCourse(Course course);
}
