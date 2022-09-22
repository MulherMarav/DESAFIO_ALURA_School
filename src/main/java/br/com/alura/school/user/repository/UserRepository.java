package br.com.alura.school.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.user.model.User;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
