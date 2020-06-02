package ru.gbjava.kinozen.services.pojo;


import lombok.*;
import ru.gbjava.kinozen.persistence.entities.Role;
import ru.gbjava.kinozen.persistence.entities.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {

    private Long id;
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

    public UserPojo(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public void addRole(Role role){
        if(roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }


}
