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
import ru.gbjava.kinozen.services.facade.AdminFacade;
import ru.gbjava.kinozen.utilites.StringConverter;
import ru.gbjava.kinozen.validators.ContentValidator;

import java.util.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ContentService contentService;
    private final ContentValidator contentValidator;
    private final AdminFacade adminFacade;


    @GetMapping
    public String startInfo(Model model) {
        adminFacade.initLinks(model);
        return "admin";
    }

    /**
     * Блок управления контентом
     */

    @GetMapping("/content")
    public String getContentList(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Date releasedFrom,
            @RequestParam(required = false) Date releasedTo,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Integer type
    ) {
        adminFacade.initLinks(model);
        List<Content> contentList = contentService.findAll(
                name,
                releasedFrom,
                releasedTo,
                visible,
                type);

        model.addAttribute("contents", ContentMapper.INSTANCE.toDtoList(contentList));
        return "adminContent";
    }

    @GetMapping("/content/add")
    public String addContent(Model model) {
        adminFacade.initLinks(model);
        model.addAttribute("content", new ContentDto());
        return "contentEdit";
    }

    @GetMapping("/content/edit/{uuid}")
    public String editContent(@PathVariable("uuid") UUID uuid, Model model) {
        adminFacade.initLinks(model);
        Content byId = contentService.findById(uuid);
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(byId));
        return "contentEdit";
    }

    @PostMapping("/content/save")
    public String saveContent(ContentDto contentDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", contentDto);
            return "redirect:/admin/content";
        }

        contentDto.setUrl(StringConverter.cyrillicToLatin(contentDto.getName()));
        UUID idContent =contentService.save(ContentMapper.INSTANCE.toEntity(contentDto)).getId();
        return "redirect:/admin/content/edit/" + idContent;
    }

    @GetMapping("/content/delete/{uuid}")
    public String deleteContent(@PathVariable("uuid") UUID uuid) {
        contentService.deleteById(uuid);
        return "redirect:/admin/content";

    }

    @GetMapping("/content/visible/{uuid}")
    public String changeVisible(@PathVariable("uuid") UUID uuid) {
        contentService.changeVisible(uuid);
        return "redirect:/admin/content";
    }

    /**
     * Блок управления сезонами
     */

    @GetMapping ("/seasons")
    public String getSeasonList(Model model){
        adminFacade.initLinks(model);
        return "adminSeasons";
    }

}
