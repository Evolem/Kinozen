package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.facade.ContentFacade;
import ru.gbjava.kinozen.services.feign.clients.PlayerFeignClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentFacade contentFacade;
    private final ContentService contentService;

    private final PlayerFeignClient playerFeignClient;
    //todo поправить логику добавления!

    @GetMapping
    public String getAllContent(Model model){
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        model.addAttribute("contentList", dtoList);
        return "contentAll";
    }

    @GetMapping ("/{url}")
    public String getContentByUrl(Model model, @PathVariable String url){
        ContentDto contentDto = ContentMapper.INSTANCE.toDto(contentFacade.findContentByUrl(url));
        model.addAttribute("content", contentDto);
        return "contentPage";
    }

    @GetMapping(value = "video/{id}")
    public ResponseEntity<byte[]> mediaSerial(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        return playerFeignClient.mediaSerial(headers, id);
    }

    @GetMapping ("/add")
    public String add(Model model) {
        ContentDto contentDto = new ContentDto();

        model.addAttribute("content", contentDto);
        return "contentEdit";
    }

    @PostMapping("/add")
    public void add(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
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
