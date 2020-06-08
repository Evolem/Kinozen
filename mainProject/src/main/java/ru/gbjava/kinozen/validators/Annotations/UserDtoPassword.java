package ru.gbjava.kinozen.validators.Annotations;

import ru.gbjava.kinozen.validators.UserDtoCurrentPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserDtoCurrentPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserDtoPassword {
    String message() default "Некорректный пароль";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
