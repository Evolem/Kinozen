package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserP {

    private String login;

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
