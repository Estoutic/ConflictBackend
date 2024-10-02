//package com.estoutic.conflict_backend.utils;
//
//import com.estoutic.conflict_backend.database.repostiories.UserRepository;
//import com.estoutic.conflict_backend.dto.UserDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//@Component
//@RequiredArgsConstructor
//public class SessionEncoder {
//
//    private final UserRepository userRepository;
//
//
//    public UserDto decodeSession() {
//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getPrincipal().toString();
//        return new UserDto(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)));
//    }
//}
