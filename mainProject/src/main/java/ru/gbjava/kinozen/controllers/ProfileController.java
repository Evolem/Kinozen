package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.pojo.UserPojo;
import ru.gbjava.kinozen.validators.UserPojoValidator;
import ru.gbjava.kinozen.validators.UserPojoValidatorPasswordOnly;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @Qualifier("userPojoValidator")
    private final UserPojoValidator userPojoValidator;

    @Qualifier("passwordValidator")
    private final UserPojoValidatorPasswordOnly passwordValidator;

    @GetMapping
    public String profilePage(final Principal principal, Model model, UserPojo userPojo) {
        final UserPojo user = userService.findByLogin(principal.getName());
        model.addAttribute("userPojo", user);
        return "profile";
    }

    @GetMapping("/password")
    public String profilePassPage(UserPojo userPojo) {
        return "profilePass";
    }

    @PostMapping("/change")
    public String changeProfile(final Principal principal, @Valid UserPojo userPojo, BindingResult bindingResult) {
        passwordValidator.validate(userPojo, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        userService.updateFieldsAndSave(principal.getName(), userPojo);
        return "redirect:/profile";
    }

    @PostMapping("/newPass")
    public String changePassword(final Principal principal, UserPojo userPojo, BindingResult bindingResult) {
        userPojoValidator.validate(userPojo, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profilePass";
        }

        userService.updateFieldsAndSave(principal.getName(), userPojo);
        return "redirect:/profile";
    }
}
