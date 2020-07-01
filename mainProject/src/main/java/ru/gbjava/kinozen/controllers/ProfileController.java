package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.beans.UserCollection;
import ru.gbjava.kinozen.services.wishlist.WishListService;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.dto.mappers.UserMapper;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.HistoryService;
import ru.gbjava.kinozen.services.SubscribeService;
import ru.gbjava.kinozen.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    // todo facade
    private final UserService userService;
    private final HistoryService historyService;
    private final UserCollection userCollection;
    private final SubscribeService subscribeService;
    private final WishListService wishListService;

    @GetMapping
    public String profilePage(final Principal principal, Model model, UserDto userDto) throws Exception {
        final User user = userService.findByLogin(principal.getName());
        model.addAttribute("userDto", UserMapper.INSTANCE.toDto(user));
        model.addAttribute("history", historyService.findHistoryByUserId(user.getId()));
        model.addAttribute("newsByActor", subscribeService.getContentSubscribeListByActor(principal.getName()));
        model.addAttribute("newsByGenre", subscribeService.getContentSubscribeListByGenre(principal.getName()));
        model.addAttribute("newEpisodes", subscribeService.getEpisodeSubscribeList(principal.getName()));

        model.addAttribute("wishList", wishListService.getWishList());
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

    //-----------------------------------------------------------------------

    /**
     * WishList
     */

    @GetMapping("/wishlist")
    public String getWishListService(Model model) {
        model.addAttribute("wishList", wishListService.getWishList());
        return "wishPage";
    }

    @GetMapping(value = "/wishlist/add/{id}")
    public void addContentToWishList(@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        wishListService.addWish(id);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping(value = "/wishlist/delete/{idContent}")
    public void deleteContentFromWishList(@PathVariable UUID idContent, HttpServletRequest request, HttpServletResponse response) throws IOException {
        wishListService.deleteWish(idContent);
        response.sendRedirect(request.getHeader("referer"));
    }

    //-----------------------------------------------------------------------
}
