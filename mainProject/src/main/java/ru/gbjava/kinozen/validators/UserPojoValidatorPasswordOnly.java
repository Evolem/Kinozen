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

@Service
@RequiredArgsConstructor
public class UserPojoValidatorPasswordOnly implements Validator {

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
        final UserPojo user = userService.findByLogin(username);
        if (!BCrypt.checkpw(userPojo.getPassword(), user.getPassword())) {
            errors.rejectValue("password", "Error", "Некорректный пароль");
        }
    }
}
