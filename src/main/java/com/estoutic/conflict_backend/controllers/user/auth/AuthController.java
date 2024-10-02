package com.estoutic.conflict_backend.controllers.user.auth;


import com.estoutic.conflict_backend.database.enitities.User;
import com.estoutic.conflict_backend.dto.UserDto;
import com.estoutic.conflict_backend.services.auth.impl.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/auth")
public class AuthController {

    private final AuthService authService;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    private final AuthenticationManager authenticationManager;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @PostMapping("/login")
    public void login(
            @RequestBody UserDto userDto,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                userDto.getUsername(), userDto.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = securityContextHolderStrategy.createEmptyContext();

        securityContext.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(securityContext);

        securityContextRepository.saveContext(securityContext, request, response);
    }

    @PostMapping("/register")
    public Integer registerUser(@RequestBody UserDto userDto){
        return authService.registerUser(userDto);
    }

    @GetMapping("/decode-session")
    public UserDto decodeSession(){
        return authService.decodeSession();
    }
}
