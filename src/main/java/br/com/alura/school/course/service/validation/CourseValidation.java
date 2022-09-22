package br.com.alura.school.course.service.validation;

import br.com.alura.school.course.model.Course;

public interface CourseValidation {

	Course validateFindByCode(String code);
}
