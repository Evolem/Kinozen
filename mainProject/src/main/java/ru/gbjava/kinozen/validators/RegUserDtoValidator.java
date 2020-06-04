package ru.gbjava.kinozen.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.services.UserService;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Service
@RequiredArgsConstructor
public class RegUserDtoValidator implements Validator {

    private final UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {

        UserDto userDto = (UserDto) target;

        if(userDto.getLogin().isEmpty())
            errors.rejectValue("login", "Error", "Введите логин");

        if(userService.isUserExist(userDto.getLogin()))
            errors.rejectValue("login", "Error", "Логин уже используется");

        if(!Pattern.compile("^(.+)@(.+)$").matcher(userDto.getEmail()).matches())
            errors.rejectValue("email", "Error", "Некороректный адрес электронной почты");

        if(userService.isEmailExist(userDto.getEmail()))
            errors.rejectValue("email", "Error", "Пользователь c почтой " + userDto.getEmail() + " уже зарегистрирован");

        if(userDto.getName().length() < 2 || userDto.getName().length() > 30)
            errors.rejectValue("name", "Error", "Некороректное имя");

        if (userDto.getNewPassword1().isEmpty() && userDto.getNewPassword2().isEmpty()) {
            errors.rejectValue("newPassword1", "Error", "Пароли не введены");
        }
        if (!Objects.equals(userDto.getNewPassword1(), userDto.getNewPassword2())) {
            errors.rejectValue("newPassword1", "Error", "Пароли не совпадают");
        }
    }


}
