package com.estoutic.conflict_backend.services.auth.impl;

import com.estoutic.conflict_backend.database.enitities.Role;
import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.RoleRepository;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.exceptions.user.UserAlreadyExistException;
import com.estoutic.conflict_backend.services.auth.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    public final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Integer registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserAlreadyExistException(userDto.getUsername());
        }
        User user = new User(userDto);
        Role role = roleRepository.findByRoleName("User");
        user.addRole(role);
        return userRepository.save(user).getId();
    }

    @Override
    public UserDto decodeSession() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getPrincipal().toString();
        return new UserDto(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
    }
}
