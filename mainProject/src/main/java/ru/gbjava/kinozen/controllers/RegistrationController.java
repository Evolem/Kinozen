package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.pojo.UserPojo;
import ru.gbjava.kinozen.validators.RegUserPojoValidator;
import ru.gbjava.kinozen.validators.UserPojoValidator;

import javax.validation.Valid;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final UserService userService;
    private final RegUserPojoValidator regUserPojoValidator;



    @GetMapping
    public String showReg(Model model){
        model.addAttribute("userPojo", new UserPojo());
        return "registration";
    }

    @PostMapping
    public String actionReg(UserPojo userPojo, Model model , BindingResult bindingResult){

        regUserPojoValidator.validate(userPojo, bindingResult);
        if(bindingResult.hasErrors()) {
            model.addAttribute("userPojo", userPojo);
            return "registration";
        }
        userService.saveNewUser(userPojo);

        return "redirect:/profile";
    }
}
