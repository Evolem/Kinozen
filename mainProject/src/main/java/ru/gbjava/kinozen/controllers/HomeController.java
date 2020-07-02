package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.services.SubscribeService;
import ru.gbjava.kinozen.services.facade.ContentFacade;
import ru.gbjava.kinozen.services.wishlist.WishListService;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContentFacade contentFacade;
    private final SubscribeService subscribeService;

    private final WishListService wishListService;

    @GetMapping
    public String index(Model model, Principal principal) throws Exception {

        Iterable<ContentDto> popularContent = ContentMapper.INSTANCE.toDtoList(contentFacade.findMostPopularContent());

        if (Objects.nonNull(principal)){
            model.addAttribute("newContents", subscribeService.getContentSubscribeList(principal.getName()));
            model.addAttribute("newEpisodes", subscribeService.getEpisodeSubscribeList(principal.getName()));
            model.addAttribute("wishlist", wishListService.getWishList());
        }

        model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent()));
        model.addAttribute("popularContentList", popularContent);
        return "index";
    }
}
