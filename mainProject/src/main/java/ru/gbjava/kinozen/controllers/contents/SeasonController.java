package ru.gbjava.kinozen.controllers.contents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.dto.SeasonDto;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.SeasonService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seasons")
public class SeasonController {

    private final SeasonService seasonService;
    private final ContentService contentService;

    @GetMapping("/")
    public String getAllSeason(Model model) {
        List<Season> seasonList = seasonService.findAll();
        model.addAttribute("seasons", seasonList);
        return "seasonAll";
    }

    @GetMapping("/edit")
    public String editSeason(Model model, @RequestParam UUID id) {
        SeasonDto seasonDto = SeasonMapper.INSTANCE.toDto(seasonService.findById(id));
        List<Content> contentList = contentService.findAll();

        model.addAttribute("seasonDto", seasonDto);
        model.addAttribute("currentContentName", contentService.findById(seasonDto.getContentId()).getName());
        model.addAttribute("contentList", contentList);

        return "seasonEdit";
    }

    @PostMapping("/saveSeason")
    public String editSeason(@ModelAttribute SeasonDto seasonDto, RedirectAttributes redirectAttributes) {

        Season seasonEntity = seasonService.findById(seasonDto.getId());
        seasonEntity.setContent(contentService.findById(seasonDto.getContentId()));
        seasonService.save(seasonEntity);

        redirectAttributes.addFlashAttribute("message",
                "сохрание успешно" + "!");

        return "redirect:/seasons/edit?id=" + seasonDto.getId();
    }
}