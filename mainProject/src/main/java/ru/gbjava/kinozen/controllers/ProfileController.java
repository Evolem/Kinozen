package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.beans.CollectionsBean;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.UserMapper;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.HistoryService;
import ru.gbjava.kinozen.services.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final HistoryService historyService;
    private final CollectionsBean collectionsBean;

    @GetMapping
    public String profilePage(final Principal principal, Model model, UserDto userDto) {
        final User user = userService.findByLogin(principal.getName());
        model.addAttribute("userDto", UserMapper.INSTANCE.toDto(user));
        model.addAttribute("history", historyService.findHistoryByUserId(user.getId()));
        return "profile";
    }

    @GetMapping("/password")
    public String profilePassPage(UserDto userDto) {
        return "profilePass";
    }

    @PostMapping("/change")
    public String changeProfile(final Principal principal, @Validated(UserDto.UserFields.class) UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }

        userService.updateFieldsAndSave(principal.getName(), userDto);
        return "redirect:/profile";
    }

    @PostMapping("/newPass")
    public String changePassword(final Principal principal, @Validated(UserDto.NewPasswords.class) UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profilePass";
        }

        userService.updateFieldsAndSave(principal.getName(), userDto);
        return "redirect:/profile";
    }

    @GetMapping("/wish")
    public String getWishCollection(Model model) {
        model.addAttribute("wishList", collectionsBean.getWishList());
        return "wishPage";
    }
}
