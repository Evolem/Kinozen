package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.services.SubscribeService;
import ru.gbjava.kinozen.services.facade.ContentFacade;
import ru.gbjava.kinozen.services.wishlist.WishListService;

import java.security.Principal;
import java.util.Objects;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContentFacade contentFacade;
    private final SubscribeService subscribeService;

    private final WishListService wishListService;

    @GetMapping
    public String index(Model model, Principal principal){
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        model.addAttribute("contentList", dtoList);
        if (principal != null){
            model.addAttribute("news", subscribeService.getContentSubscribeList(principal.getName()));
        }
    public String index(Model model, Principal principal) {
        if (Objects.nonNull(principal)) {
            model.addAttribute("wishlist", wishListService.getWishList());
        }
        model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent()));
        return "index";
    }
}
