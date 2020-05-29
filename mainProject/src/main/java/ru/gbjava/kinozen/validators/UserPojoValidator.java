package ru.gbjava.kinozen.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.pojo.UserPojo;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPojoValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserPojo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        UserPojo userPojo = (UserPojo) target;
        final UserPojo locatedUser = userService.findByLogin(username);

        if (userPojo.getNewPassword1().isEmpty() && userPojo.getNewPassword2().isEmpty()) {
            errors.rejectValue("newPassword1", "Error", "Пароли не введены");
        }
        if (!Objects.equals(userPojo.getNewPassword1(), userPojo.getNewPassword2())) {
            errors.rejectValue("newPassword1", "Error", "Пароли не совпадают");
        }
        if (!BCrypt.checkpw(userPojo.getPassword(), locatedUser.getPassword())) {
            errors.rejectValue("password", "Error", "Некорректный пароль");
        }
    }
}
