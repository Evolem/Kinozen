package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.services.ContentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ContentService contentService;

    @GetMapping
    public String index(Model model){
        List<ContentDto> dtoList = contentService.getAllContent();
        model.addAttribute("contentList", dtoList);
        return "index";
    }
}
