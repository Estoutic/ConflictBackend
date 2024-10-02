package com.estoutic.conflict_backend.services.auth;

import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.dto.UserDto;

import java.util.UUID;

public interface IAuthService {

    UUID registerUser(UserDto userDto);

    UserDto decodeSession();

    User getUserBySession();

}
