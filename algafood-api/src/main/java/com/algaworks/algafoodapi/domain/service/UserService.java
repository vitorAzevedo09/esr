package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.GroupNotFoundException;
import com.algaworks.algafoodapi.domain.exception.UserNotFoundException;
import com.algaworks.algafoodapi.domain.model.User;
import com.algaworks.algafoodapi.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public User findOrFail(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GroupNotFoundException(userId));
    }

    @Transactional
    public User create(User user) {
        Optional<User> exist = userRepository.findByEmail(user.getEmail());
        if (exist.isPresent() && !exist.get().equals(user)) {
            throw new RuntimeException("Usuario com este email já existente");
        }
        return userRepository.save(user);
    }

    @Transactional
    public User update(final Long id, User user) {
        if(!userRepository.existsById(id))
        throw new UserNotFoundException(id);
        user.setId(id);
        return userRepository.save(user);
    }

    @Transactional
    public void changePassword(Long userId, String actualPassword, String newPassword) {
        User user = findOrFail(userId);
        if (user.passwordDontMatches(actualPassword)) {
            throw new RuntimeException("Senha atual informada não coincide com a senha do usuário.");
        }
    }

}
