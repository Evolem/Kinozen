package ru.gbjava.kinozen.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.Content;
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

    public interface UserFields {
    }

    public interface NewPasswords {
    }

    private UUID id;
    private String login;

    @Size(min = 2, max = 30, groups = UserFields.class)
    private String name;

    @NotEmpty(groups = UserFields.class)
    @Email(groups = UserFields.class)
    private String email;

    @UserDtoPassword(groups = {UserFields.class, NewPasswords.class})
    private String password;

    private Set<Role> roles;

    private Set<Content> likedContent;
    private Set<Content> dislikedContent;

    private String newPassword1;
    private String newPassword2;

    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
