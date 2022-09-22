package br.com.alura.school.user.service.validation;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.user.service.UserRepository;

@Component
public class UserValidationImpl implements UserValidation {
	
	private final UserRepository userRepository;
	

	public UserValidationImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User userByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", username)));
		return user;
	}
}
