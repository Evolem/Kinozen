package ru.gbjava.kinozen.dto;


import lombok.*;
import ru.gbjava.kinozen.persistence.entities.Role;
import ru.gbjava.kinozen.persistence.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String login;

    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Email
    private String email;
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
