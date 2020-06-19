package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.facade.AdminFacade;
import ru.gbjava.kinozen.services.facade.StorageFacade;
import ru.gbjava.kinozen.utilites.StringConverter;
import ru.gbjava.kinozen.validators.ContentValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/content")
@RequiredArgsConstructor
public class ContentManagementController {


    // todo доделать фасад
    private final ContentService contentService;
    private final ContentValidator contentValidator;
    private final AdminFacade adminFacade;
    private final StorageFacade storageFacade;

    /**
     * Блок управления контентом
     */

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
        List<Content> contentList = contentService.findAll(
                name,
                releasedFrom,
                releasedTo,
                visible,
                type);

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
    public String saveContent(ContentDto contentDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("content", contentDto);
            return "redirect:/admin/content";
        }

        contentDto.setUrl(StringConverter.cyrillicToLatin(contentDto.getName()));
        UUID idContent = contentService.save(ContentMapper.INSTANCE.toEntity(contentDto)).getId();
        return "redirect:/admin/content/edit/" + idContent;
    }

    @PostMapping("/uploadImage")
    public void handleFileUpload(@RequestParam(required = false, name = "file") MultipartFile file,
                                 @ModelAttribute Content content,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        storageFacade.uploadImageContent(file, content);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        response.sendRedirect(request.getHeader("referer"));
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
