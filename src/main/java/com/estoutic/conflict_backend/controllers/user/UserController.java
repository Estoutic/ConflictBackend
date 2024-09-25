package com.estoutic.conflict_backend.controllers.user;


import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.auth.impl.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers(){
        return userService.getAll();
    }

}

