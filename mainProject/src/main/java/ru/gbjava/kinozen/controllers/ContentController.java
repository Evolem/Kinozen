package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.pojo.ContentPojo;
import ru.gbjava.kinozen.services.pojo.TypeContentPojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
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

    @GetMapping ("/addContent")
    public String addGetContent(Model model) {
        ContentPojo contentPojo = new ContentPojo();
        List<TypeContentPojo> types = contentService.getAllTypes();
        model.addAttribute("content" ,contentPojo);
        model.addAttribute("types",types);
        return "addContent";
    }

    @PostMapping("/addContent")
    public void addContent(ContentPojo contentPojo, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentService.save(contentPojo);
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping ("/delete")
    public String deleteMedia(){
        return "deleteContent";
    }


}
