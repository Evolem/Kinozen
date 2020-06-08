package ru.gbjava.kinozen.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.dto.UserDto;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDtoValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        UserDto userDto = (UserDto) target;
        final User user = userService.findByLogin(username);

        if (userDto.getNewPassword1().isEmpty() && userDto.getNewPassword2().isEmpty()) {
            errors.rejectValue("newPassword1", "Error", "Пароли не введены");
        }
        if (!Objects.equals(userDto.getNewPassword1(), userDto.getNewPassword2())) {
            errors.rejectValue("newPassword1", "Error", "Пароли не совпадают");
        }
        if (!BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            errors.rejectValue("password", "Error", "Некорректный пароль");
        }
    }
}
