package ru.gbjava.kinozen.validators.Annotations;

import ru.gbjava.kinozen.validators.UserDtoNewPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserDtoNewPasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserDtoPasswordMatcher {

    String field();

    String secondField();

    String message() default "Некорректный новый пароль";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
