package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.validators.RegUserDtoValidator;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/registration")
public class RegistrationController {

    //todo фасад?
    private final UserService userService;
    private final RegUserDtoValidator regUserDtoValidator;

    @GetMapping
    public String showReg(Model model){
        model.addAttribute("userDto", new UserDto());
        return "registration";
    }

    @PostMapping
    public String actionReg(UserDto userDto, Model model , BindingResult bindingResult){
        regUserDtoValidator.validate(userDto, bindingResult);

        if(bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "registration";
        }
        userService.saveNewUser(userDto);
        return "redirect:/profile";
    }
}
