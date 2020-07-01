package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.utilites.ContentFilter;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SearchController {
    private final ContentService contentService;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("content", new Content ());
        return "searchPage";
    }
    @GetMapping("/search/results")
    public String search(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 6);
        ContentFilter contentFilter = new ContentFilter(params);
        Page<Content> page = contentService.findAll(contentFilter.getSpec(), pageRequest);
        model.addAttribute("content", new Content ());
        model.addAttribute("filtersDef", contentFilter.getFilterDefinition());
        model.addAttribute("page", page);
        return "searchPage";
    }
}

