package com.estoutic.conflict_backend.services.auth.impl;

import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.auth.IAuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {

    public final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return null;
            //throw exception
        }
        User user = new User(userDto);
        return userRepository.save(user).id;

    }

    @Override
    public UserDto decodeSession() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getPrincipal().toString();
        return new UserDto(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
    }
}
