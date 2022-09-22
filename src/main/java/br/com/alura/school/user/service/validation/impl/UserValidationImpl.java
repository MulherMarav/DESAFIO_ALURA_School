package br.com.alura.school.user.service.validation.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.user.model.User;
import br.com.alura.school.user.model.UserRole;
import br.com.alura.school.user.repository.UserRepository;
import br.com.alura.school.user.service.validation.InstructorValidation;
import br.com.alura.school.user.service.validation.UserValidation;

@Component
public class UserValidationImpl implements UserValidation, InstructorValidation {
	
	private final UserRepository userRepository;
	

	public UserValidationImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User validateUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
		return user;
	}

	@Override
	public void isAnInstructor(User user) {
		if(user.getRole() != UserRole.INSTRUCTOR)
			new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Author %s must be an instructor", user.getUsername()));
	}
}
