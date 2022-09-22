package br.com.alura.school.user.service;

import static java.lang.String.format;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.alura.school.user.service.validation.UserValidation;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	private final UserValidation userValidation;

	public UserService(br.com.alura.school.user.service.UserRepository userRepository, UserValidation userValidation) {
		this.userRepository = userRepository;
		this.userValidation = userValidation;
	}

	public UserResponse userByUsername(String username) {
		User user = userValidation.userByUsername(username);
		return new UserResponse(user);
	}

	public URI newUser(@Valid NewUserRequest newUserRequest) {
		userRepository.save(newUserRequest.toEntity());
		URI location = URI.create(format("/users/%s", newUserRequest.getUsername()));
		return location;
	}
}
