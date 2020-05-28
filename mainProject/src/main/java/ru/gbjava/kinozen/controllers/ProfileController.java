package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.pojo.UserP;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public String profilePage(Principal principal, Model model, UserP userP){
        User user = userService.findByLogin(principal.getName());
        userP.setEmail(user.getEmail());
        userP.setName(user.getName());
        model.addAttribute("userP", userP);
        return "profile";
    }

    @GetMapping("/password")
    public String profilePassPage(UserP userP){
        return "profilePass";
    }

    @PostMapping("/change")
    public String changeProfile(Principal principal, @Valid UserP userP, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        User user = userService.findByLogin(principal.getName());
        if (!BCrypt.checkpw(userP.getPassword(), user.getPassword())) {
            bindingResult.rejectValue("password", "Error", "Некорректный пароль");
            return "profile";
        }

        user.setEmail(userP.getEmail());
        user.setName(userP.getName());
        userService.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/newPass")
    public String changePassword(Principal principal, UserP userP, BindingResult bindingResult) {
        User user = userService.findByLogin(principal.getName());

        if (userP.getNewPassword1().isEmpty() && userP.getNewPassword2().isEmpty()) {
            bindingResult.rejectValue("newPassword1", "Error", "Пароли не введены");
            return "profilePass";
        }
        if (!Objects.equals(userP.getNewPassword1(), userP.getNewPassword2())) {
            bindingResult.rejectValue("newPassword1", "Error", "Пароли не совпадают");
            return "profilePass";
        }
        if (!BCrypt.checkpw(userP.getPassword(), user.getPassword())) {
            bindingResult.rejectValue("password", "Error", "Некорректный пароль");
            return "profilePass";
        }

        user.setPassword(new BCryptPasswordEncoder().encode(userP.getNewPassword1()));
        userService.save(user);
        return "redirect:/profile";
    }
}
