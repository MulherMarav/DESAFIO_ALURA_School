package br.com.alura.school.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.alura.school.user.model.User;

public class UserResponse {

    @JsonProperty
    private final String username;

    @JsonProperty
    private final String email;

    public UserResponse(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
