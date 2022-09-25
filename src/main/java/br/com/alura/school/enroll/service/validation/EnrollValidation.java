package br.com.alura.school.enroll.service.validation;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.user.model.User;

public interface EnrollValidation {

	void validateEnrollByUserAndCourse(User user, Course course);
}
