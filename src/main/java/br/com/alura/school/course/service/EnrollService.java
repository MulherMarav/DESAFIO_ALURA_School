package br.com.alura.school.course.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.course.model.Course;
import br.com.alura.school.course.repository.EnrollRepository;
import br.com.alura.school.course.request.NewEnrollRequest;
import br.com.alura.school.course.service.validation.CourseValidation;
import br.com.alura.school.course.service.validation.EnrollValidation;
import br.com.alura.school.user.model.User;
import br.com.alura.school.user.service.validation.UserValidation;

@Service
public class EnrollService {
	
	private final UserValidation userValidation;
	
	private final CourseValidation courseValidation;
	
	private final EnrollValidation enrollValidation;
	
	private final EnrollRepository enrollRepository;


	public EnrollService(UserValidation userValidation, CourseValidation courseValidation,
			EnrollValidation enrollValidation, EnrollRepository enrollRepository) {
		this.userValidation = userValidation;
		this.courseValidation = courseValidation;
		this.enrollValidation = enrollValidation;
		this.enrollRepository = enrollRepository;
	}

	public void newEnroll(@Valid NewEnrollRequest newEnrollRequest, String courseCode) {
		
		String username = newEnrollRequest.getUsername();
		
		User user = userValidation.validateUserByUsername(username);
		
		Course course = courseValidation.validateFindByCode(courseCode);
		
		enrollValidation.validateEnrollByUserAndCourse(user, course);
		
		enrollRepository.save(newEnrollRequest.toEntity(user, course));
	}
}
