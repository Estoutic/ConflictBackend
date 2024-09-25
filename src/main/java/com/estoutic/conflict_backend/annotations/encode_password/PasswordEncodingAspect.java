package com.estoutic.conflict_backend.annotations.encode_password;

import com.estoutic.conflict_backend.dto.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class PasswordEncodingAspect {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Before("execution(com.estoutic.conflict_backend.database.enitities.User.new(..)) && @annotation(com.estoutic.conflict_backend.annotations.encode_password.EncodePassword)")
    public void encodePassword(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        for (Object arg : args) {
            if(arg instanceof UserDto userDto){
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
        }
    }
}
