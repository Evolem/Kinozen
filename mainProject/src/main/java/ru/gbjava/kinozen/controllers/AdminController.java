package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.validators.ContentValidator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ContentService contentService;
    private final ContentValidator contentValidator;

    @GetMapping
    public String list(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Date releasedFrom,
            @RequestParam(required = false) Date releasedTo,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Integer type
    ) {

        List<Content> contentList = contentService.findAll(
                name,
                releasedFrom,
                releasedTo,
                visible,
                type);
        model.addAttribute("content", ContentMapper.INSTANCE.toDtoList(contentList));
        return "admin";
    }

    @GetMapping(value = "/{uuid}")
    public String show(@PathVariable("uuid") UUID uuid, Model model){
        Content byId = contentService.findById(uuid);
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(byId));
        return "contentPage";
    }

    @GetMapping(value = "/edit/{uuid}")
    public String update(@PathVariable("uuid")UUID uuid, Model model){
        Content byId = contentService.findById(uuid);
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(byId));
        return "contentEdit";
    }

    @GetMapping(value = "/delete/{uuid}")
    public String delete(@PathVariable("uuid")UUID uuid){
        contentService.deleteById(uuid);
        return "redirect:/admin";

    }

    @PostMapping
    public String saveWorkflow(ContentDto contentDto, BindingResult bindingResult, Model model) {


        contentValidator.validate(contentDto, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("content",  contentDto);
            return "contentEdit";

        }
        Content content = contentService.save(ContentMapper.INSTANCE.toEntity(contentDto));
        return "redirect:/admin/" + content.getId();
    }


    @GetMapping("/new")
    public String newWorkflow(Model model){

        model.addAttribute("content",
                new ContentDto()

        );
        return "contentEdit";
    }


}
