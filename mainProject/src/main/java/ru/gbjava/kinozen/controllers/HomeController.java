package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.services.facade.ContentFacade;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContentFacade contentFacade;
    @GetMapping
    public String index(Model model){
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        Iterable<ContentDto> popularContent = ContentMapper.INSTANCE.toDtoList(contentFacade.findMostPopularContent());
        model.addAttribute("contentList", dtoList);
        model.addAttribute("popularContentList", popularContent);
        return "index";
    }
}
