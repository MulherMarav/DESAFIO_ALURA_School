package br.com.alura.school.user;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserResponse userByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", username)));
		return new UserResponse(user);
	}

	public URI newUser(@Valid NewUserRequest newUserRequest) {
		userRepository.save(newUserRequest.toEntity());
		URI location = URI.create(format("/users/%s", newUserRequest.getUsername()));
		return location;
	}
}
