package com.estoutic.conflict_backend.controllers.user.auth;


import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.impl.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private  final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/user")
    public Integer registerUser(@RequestBody UserDto userDto){
        return registrationService.registerUser(userDto);
    }

}
