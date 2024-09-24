//package com.estoutic.conflict_backend.annotations.password_mathces;
//
//import com.estoutic.conflict_backend.dto.UserDto;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
//    @Override
//    public void initialize(PasswordMatches constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }
//
//    @Override
//    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
//        UserDto user = (UserDto) o;
//        return user.getPassword().equals(user.getMatchesPassword());
//    }
//}
