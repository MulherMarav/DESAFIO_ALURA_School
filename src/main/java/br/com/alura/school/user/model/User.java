package br.com.alura.school.user.model;

import static br.com.alura.school.user.model.UserRole.STUDENT;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role = STUDENT;

    @Deprecated
    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
    
    public User(@Size(max = 20) @NotBlank String username, @NotBlank @Email String email, UserRole role) {
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
		return role;
	}
}
