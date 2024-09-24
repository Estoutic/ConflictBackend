package com.estoutic.conflict_backend.services.impl;

import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.database.repostiories.UserRepository;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.IRegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService implements IRegistrationService {

    public final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Integer registerUser(UserDto userDto) {
        if (userRepository.existsByUserName(userDto.getUserName())){
            return null;
            //throw exception
        }
        User user = new User(userDto);
        return userRepository.save(user).id;

    }

    @Override
    public List<UserDto> getUserInfo() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }
}
