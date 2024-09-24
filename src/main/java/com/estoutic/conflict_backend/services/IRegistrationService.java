package com.estoutic.conflict_backend.services;

import com.estoutic.conflict_backend.dto.UserDto;

import java.util.List;

public interface IRegistrationService {

    Integer registerUser(UserDto userDto);

    List<UserDto> getUserInfo();
}
