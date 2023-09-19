package com.algaworks.algafoodapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algafoodapi.domain.model.User;

/**
 * UsuarioRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
