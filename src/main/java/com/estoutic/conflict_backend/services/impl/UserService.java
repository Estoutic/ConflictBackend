package com.estoutic.conflict_backend.services.impl;


import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }
}

