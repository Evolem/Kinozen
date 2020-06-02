package ru.gbjava.kinozen.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.pojo.UserPojo;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Service
@RequiredArgsConstructor
public class RegUserPojoValidator implements Validator {

    private final UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return UserPojo.class.equals(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {

        UserPojo userPojo = (UserPojo) target;

        if(userPojo.getLogin().isEmpty())
            errors.rejectValue("login", "Error", "Введите логин");

        if(userService.isUserExist(userPojo.getLogin()))
            errors.rejectValue("login", "Error", "Логин уже используется");

        if(!Pattern.compile("^(.+)@(.+)$").matcher(userPojo.getEmail()).matches())
            errors.rejectValue("email", "Error", "Некороректный адрес электронной почты");

        if(userService.isEmailExist(userPojo.getEmail()))
            errors.rejectValue("email", "Error", "Пользователь c почтой " + userPojo.getEmail() + " уже зарегистрирован");

        if(userPojo.getName().length() < 2 || userPojo.getName().length() > 30)
            errors.rejectValue("name", "Error", "Некороректное имя");

        if (userPojo.getNewPassword1().isEmpty() && userPojo.getNewPassword2().isEmpty()) {
            errors.rejectValue("newPassword1", "Error", "Пароли не введены");
        }
        if (!Objects.equals(userPojo.getNewPassword1(), userPojo.getNewPassword2())) {
            errors.rejectValue("newPassword1", "Error", "Пароли не совпадают");
        }
    }


}
