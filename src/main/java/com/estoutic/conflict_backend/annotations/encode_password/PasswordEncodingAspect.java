package com.estoutic.conflict_backend.annotations.encode_password;


import com.estoutic.conflict_backend.dto.UserDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordEncodingAspect {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Before("execution(*.new(..)) && @annotation(com.estoutic.conflict_backend.annotations.encode_password.EncodePassword)")
    public void encodePassword(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        for (Object arg : args) {
            if(arg instanceof UserDto){
                UserDto userDto = (UserDto) arg;
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
        }
    }
}
