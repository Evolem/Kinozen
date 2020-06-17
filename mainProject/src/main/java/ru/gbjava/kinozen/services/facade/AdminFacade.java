package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.SeasonService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminFacade {

    private final SeasonService seasonService;
    private final ContentService contentService;

    // todo реализовать на уровне бд
    public void initLinks(Model model) {
        Map<String, String> links = new LinkedHashMap<>();
        links.put("content", "Content management");
        links.put("comments", "Comments");
        links.put("banners", "Banners");
        links.put("users", "Users");
        model.addAttribute("links", links);
    }

    public Iterable<Season> getSeasonByContent(Content content) {
        return seasonService.findSeasonByContent(content);
    }
}
