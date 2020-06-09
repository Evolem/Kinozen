package ru.gbjava.kinozen.validators;

import org.springframework.beans.BeanWrapperImpl;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.validators.Annotations.UserDtoPasswordMatcher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserDtoNewPasswordValidator implements ConstraintValidator<UserDtoPasswordMatcher, UserDto> {

    private String field;
    private String secondField;

    @Override
    public void initialize(UserDtoPasswordMatcher annotation) {
        this.field = annotation.field();
        this.secondField = annotation.secondField();
    }

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        String fieldVal = (String) new BeanWrapperImpl(value).getPropertyValue(field);
        String secondFieldVal = (String) new BeanWrapperImpl(value).getPropertyValue(secondField);

        if (fieldVal.isEmpty() && secondFieldVal.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пароли не введены")
                    .addPropertyNode(field).addConstraintViolation();
            return false;
        }
        if (!Objects.equals(fieldVal, secondFieldVal)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пароли не совпадают")
                    .addPropertyNode(field).addConstraintViolation();
            return false;
        }
        return true;
    }
}
