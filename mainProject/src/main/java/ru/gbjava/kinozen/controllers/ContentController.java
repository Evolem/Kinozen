package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.TypeContentDto;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final GenreService genreService;

    @GetMapping
    public String getAllContent(Model model){
        List<ContentDto> dtoList = contentService.getAllContent();
        model.addAttribute("contentList", dtoList);
        return "content";
    }

    //todo dto and facade
    @GetMapping ("/{url}")
    public String getContentByUrl(Model model, @PathVariable String url){
        Content contentDto = contentService.findByUrl(url);
        model.addAttribute("content", contentDto);
        return "contentPage";
    }

    @GetMapping ("/addContent")
    public String addContent(Model model) {
        ContentDto contentDto = new ContentDto();
        List<TypeContentDto> types = contentService.getAllTypes();
        model.addAttribute("content", contentDto);
        model.addAttribute("types", types);
        return "addContent";
    }

    @PostMapping("/addContent")
    public void addContent(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentService.save(contentDto);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping ("/delete")
    public void deleteContent(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentService.delete(contentDto);
        response.sendRedirect(request.getHeader("referer"));
    }


}
