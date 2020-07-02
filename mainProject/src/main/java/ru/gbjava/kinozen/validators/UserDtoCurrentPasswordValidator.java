package ru.gbjava.kinozen.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.validators.Annotations.UserDtoPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
public class UserDtoCurrentPasswordValidator implements ConstraintValidator<UserDtoPassword, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        final User user = userService.findByLogin(username);

        return BCrypt.checkpw(value, user.getPassword());
    }
}
