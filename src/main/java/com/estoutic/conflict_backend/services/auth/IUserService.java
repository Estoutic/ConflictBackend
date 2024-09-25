package com.estoutic.conflict_backend.services.auth;

import com.estoutic.conflict_backend.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {

    List<UserDto> getAll();

    UserDetails loadUserByUsername(String username);
}
