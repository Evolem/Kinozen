package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.validators.UserDtoValidator;
import ru.gbjava.kinozen.validators.UserDtoValidatorPasswordOnly;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    //todo переделать!

    @Qualifier("userDtoValidator")
    private final UserDtoValidator userDtoValidator;

    @Qualifier("passwordValidator")
    private final UserDtoValidatorPasswordOnly passwordValidator;

    @GetMapping
    public String profilePage(final Principal principal, Model model, UserDto userDto) {
        final UserDto user = userService.findByLogin(principal.getName());
        model.addAttribute("userPojo", user);
        return "profile";
    }

    @GetMapping("/password")
    public String profilePassPage(UserDto userDto) {
        return "profilePass";
    }

    @PostMapping("/change")
    public String changeProfile(final Principal principal, @Valid UserDto userDto, BindingResult bindingResult) {
        passwordValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        userService.updateFieldsAndSave(principal.getName(), userDto);
        return "redirect:/profile";
    }

    @PostMapping("/newPass")
    public String changePassword(final Principal principal, UserDto userDto, BindingResult bindingResult) {
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "profilePass";
        }

        userService.updateFieldsAndSave(principal.getName(), userDto);
        return "redirect:/profile";
    }
}
