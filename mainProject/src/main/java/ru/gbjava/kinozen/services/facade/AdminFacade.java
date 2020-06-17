package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminFacade {

    public void initLinks(Model model) {
        Map<String, String> links = new LinkedHashMap<>();
        links.put("content", "Content management");
        links.put("comments", "Comment system");
        links.put("banners", "Banners");
        links.put("users", "Users");
        model.addAttribute("links",links);
    }
}
