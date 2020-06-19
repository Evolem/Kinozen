package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.facade.AdminFacade;
import ru.gbjava.kinozen.validators.ContentValidator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/content")
@RequiredArgsConstructor
public class ContentManagementController {

    /**
     * Управление контентом
     */

    // todo доделать фасад
    private final ContentService contentService;
    private final ContentValidator contentValidator;
    private final AdminFacade adminFacade;

    @GetMapping
    public String getContentList(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Date releasedFrom,
            @RequestParam(required = false) Date releasedTo,
            @RequestParam(required = false) Boolean visible,
            @RequestParam(required = false) Integer type
    ) {
        adminFacade.initLinks(model);
        List<Content> contentList = adminFacade.getContentsByFilers(name, releasedFrom, releasedTo, visible, type);
        model.addAttribute("contents", ContentMapper.INSTANCE.toDtoList(contentList));
        return "adminPanel/adminContent";
    }

    @GetMapping("/add")
    public String addContent(Model model) {
        adminFacade.initLinks(model);
        model.addAttribute("content", new ContentDto());
        return "adminPanel/contentEdit";
    }

    @GetMapping("/edit/{uuid}")
    public String editContent(@PathVariable("uuid") UUID uuid, Model model) {
        adminFacade.initLinks(model);
        Content content = contentService.findById(uuid);

        if (content.getType() == TypeContent.SERIAL) {
            Iterable<Season> seasons = adminFacade.getSeasonByContent(content);
            model.addAttribute("seasons", seasons);
        }

        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        return "adminPanel/contentEdit";
    }

    @PostMapping("/save")
    public String saveContent(@RequestParam(required = false, name = "file") MultipartFile file,
                              ContentDto contentDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", contentDto);
            return "redirect:/admin/content";
        }

        Content content = adminFacade.saveContent(ContentMapper.INSTANCE.toEntity(contentDto), file);

        return "redirect:/admin/content/edit/" + content.getId();
    }


    @GetMapping("/delete/{uuid}")
    public String deleteContent(@PathVariable("uuid") UUID uuid) {
        contentService.deleteById(uuid);
        return "redirect:/admin/content";

    }

    @GetMapping("/visible/{uuid}")
    public String changeVisible(@PathVariable("uuid") UUID uuid) {
        contentService.changeVisible(uuid);
        return "redirect:/admin/content";
    }


}
