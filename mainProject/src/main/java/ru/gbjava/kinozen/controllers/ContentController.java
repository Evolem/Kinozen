package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.pojo.ContentPojo;

import java.util.List;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public String getAllMedia(Model model){
        List<ContentPojo> pojoList = contentService.getAllMedia();
        model.addAttribute("contentList", pojoList);
        return "content";
    }

    @GetMapping ("/{url}")
    public String getMediaByUrl(Model model, @PathVariable String url){
        ContentPojo contentPojo = contentService.findByUrl(url);
        model.addAttribute("content", contentPojo);
        return "contentPage";
    }

}
