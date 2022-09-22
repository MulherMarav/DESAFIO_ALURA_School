package br.com.alura.school.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.user.model.User;

class UserResponse {

    @JsonProperty
    private final String username;

    @JsonProperty
    private final String email;

    UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
