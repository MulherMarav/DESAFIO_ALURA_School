package br.com.alura.school.user.service.validation;

import br.com.alura.school.user.model.User;

public interface UserValidation {
	
	User validateUserByUsername(String username);
}
