package org.example.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.spring.validation.impl.UserInfoValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {
    String message() default "Firstname or lastname should be fill in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
