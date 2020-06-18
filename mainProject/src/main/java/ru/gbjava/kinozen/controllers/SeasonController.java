package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.SeasonService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonService seasonService;
    private final ContentService contentService;

    @GetMapping("/")
    public String getAllSeason(Model model) {
        List<Season> seasonList = seasonService.findAll();
        model.addAttribute("seasons", seasonList);
        return "seasonAll";
    }
}
