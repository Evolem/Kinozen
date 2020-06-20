package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.dto.SeasonDto;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.services.facade.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin/season")
@RequiredArgsConstructor
public class SeasonManagementController {

    /**
     *  Управление сезонами
     */

    private final AdminFacade adminFacade;

    @GetMapping("/add/{idContent}")
    public String addSeason(Model model, @PathVariable UUID idContent) {
        adminFacade.initLinks(model);
        SeasonDto seasonDto = new SeasonDto();
        seasonDto.setContent(adminFacade.findContentById(idContent));
        model.addAttribute("season", seasonDto);
        return "adminPanel/seasonEdit";
    }

    @GetMapping("/edit/{id}")
    public String editSeason(Model model, @PathVariable UUID id) {
        SeasonDto seasonDto = SeasonMapper.INSTANCE.toDto(adminFacade.findSeasonById(id));
        model.addAttribute("season", seasonDto);
        return "adminPanel/seasonEdit";
    }

    @PostMapping("/save")
    public String saveSeason(@ModelAttribute SeasonDto seasonDto, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                "сохрание успешно" + "!");

        adminFacade.saveSeason(SeasonMapper.INSTANCE.toEntity(seasonDto));
        return "redirect:/admin/content/edit/" + seasonDto.getContent().getId();
    }

    @GetMapping("/delete/{id}")
    public void deleteSeason(@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminFacade.deleteSeasonById(id);
        response.sendRedirect(request.getHeader("referer"));
    }
}
