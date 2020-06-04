package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.ContentTypeDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.ContentTypeMapper;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.facade.ContentFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentFacade contentFacade;
    private final ContentService contentService;

    //todo поправить логику добавления!

    @GetMapping
    public String getAllContent(Model model){
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        model.addAttribute("contentList", dtoList);
        return "content";
    }

    @GetMapping ("/{url}")
    public String getContentByUrl(Model model, @PathVariable String url){
        ContentDto contentDto = ContentMapper.INSTANCE.toDto(contentFacade.findContentByUrl(url));
        model.addAttribute("content", contentDto);
        return "contentPage";
    }

    @GetMapping ("/addContent")
    public String addContent(Model model) {
        ContentDto contentDto = new ContentDto();
        Iterable<ContentTypeDto> types = ContentTypeMapper.INSTANCE.toDtoList(contentFacade.findAllTypeContent());

        model.addAttribute("content", contentDto);
        model.addAttribute("types", types);
        return "addContent";
    }

    @PostMapping("/addContent")
    public void addContent(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentFacade.saveContent(ContentMapper.INSTANCE.toEntity(contentDto));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping ("/delete")
    public void deleteContent(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentFacade.deleteContentById(contentDto.getId());
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/")
    public void generateAllUrl(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam String param) throws IOException {
        if (param.equals("re")) {
            contentService.reGenerateAllUrl();
        }
        response.sendRedirect(request.getHeader("referer"));
    }

}
