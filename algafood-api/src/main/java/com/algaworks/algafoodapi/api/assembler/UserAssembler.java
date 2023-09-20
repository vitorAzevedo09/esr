package com.algaworks.algafoodapi.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafoodapi.api.dto.UserInput;
import com.algaworks.algafoodapi.api.dto.UserOutput;
import com.algaworks.algafoodapi.api.dto.UserWithPasswordInput;
import com.algaworks.algafoodapi.domain.model.User;

/**
 * UsuarioAssembler
 */
@Component
public class UserAssembler {

    public User toEntity(UserWithPasswordInput userInput) {
        return User.Builder
                .newInstance()
                .setName(userInput.name())
                .setEmail(userInput.email())
                .setPassword(userInput.password())
                .build();
    }


    public User toEntity(UserInput userInput) {
        return User.Builder
                .newInstance()
                .setName(userInput.name())
                .setEmail(userInput.email())
                .build();
    }

    public User copyToEntity(UserInput input, User user) {
        user.setName(input.name());
        user.setEmail(input.email());
        return user;
    }

    public UserOutput toOutput(User user) {
        return new UserOutput(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}
