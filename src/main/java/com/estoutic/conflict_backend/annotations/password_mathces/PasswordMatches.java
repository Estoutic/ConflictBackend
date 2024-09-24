//package com.estoutic.conflict_backend.annotations.password_mathces;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Documented;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
//import static java.lang.annotation.ElementType.TYPE;
//
//@Target({TYPE, ANNOTATION_TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = PasswordMatchesValidator.class)
//@Documented
//public @interface PasswordMatches {
//
//    String message() default "Password dont match :(";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
