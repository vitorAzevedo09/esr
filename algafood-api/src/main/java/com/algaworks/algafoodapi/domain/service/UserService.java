package com.algaworks.algafoodapi.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.algaworks.algafoodapi.domain.exception.GroupConflictIdException;
import com.algaworks.algafoodapi.domain.exception.UserNotFoundException;
import com.algaworks.algafoodapi.domain.model.Group;
import com.algaworks.algafoodapi.domain.model.User;
import com.algaworks.algafoodapi.domain.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService gService;

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public User findOrFail(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
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
        if (!userRepository.existsById(id))
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

    @Transactional
    public void addGroupToUser(final Long userID, final Long groupID) {
        User u = findOrFail(userID);
        Group g = gService.findOrFail(groupID);
        if (!u.getGroups().contains(g)) {
            u.addGroup(g);
        }
        throw new GroupConflictIdException(groupID);
    }

    @Transactional
    public void removeGroupToUser(final Long userID, final Long groupID) {
        User u = findOrFail(userID);
        Group g = gService.findOrFail(groupID);
        u.removeGroup(g);
    }

}
