package ru.gbjava.kinozen.dto;


import lombok.*;
import ru.gbjava.kinozen.persistence.entities.Role;
import ru.gbjava.kinozen.validators.Annotations.UserDtoPassword;
import ru.gbjava.kinozen.validators.Annotations.UserDtoPasswordMatcher;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@UserDtoPasswordMatcher(
        field = "newPassword1",
        secondField = "newPassword2",
        groups = UserDto.NewPasswords.class
)
public class UserDto {
    interface CurrentPassword {
    }

    public interface OnlyFields extends CurrentPassword {
    }

    public interface NewPasswords extends CurrentPassword {
    }

    private UUID id;
    private String login;

    @Size(min = 2, max = 30, groups = OnlyFields.class)
    private String name;

    @NotEmpty(groups = OnlyFields.class)
    @Email(groups = OnlyFields.class)
    private String email;

    @UserDtoPassword(groups = CurrentPassword.class)
    private String password;

    private Set<Role> roles;

    private String newPassword1;
    private String newPassword2;

    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
