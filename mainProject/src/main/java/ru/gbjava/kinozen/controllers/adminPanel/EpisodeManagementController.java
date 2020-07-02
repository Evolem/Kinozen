package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.dto.SeasonDto;
import ru.gbjava.kinozen.dto.mappers.EpisodeMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.facade.AdminFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin/episode")
@RequiredArgsConstructor
public class EpisodeManagementController {

    /**
     * Управление эпизодами
     */

    private final AdminFacade adminFacade;

    @ModelAttribute("links")
    public Map<String, String> getLinksForMenu() {
        return adminFacade.initLinks();
    }

    @GetMapping("/add/{idSeason}")
    public String addSeason(Model model, @PathVariable UUID idSeason) {
        Season season = adminFacade.findSeasonById(idSeason);
        EpisodeDto episodeDto = new EpisodeDto();
        episodeDto.setSeason(season);
        episodeDto.setNumberEpisode(season.getEpisodes().size() + 1);
        episodeDto.setDescription("Описание отсутствует");
        model.addAttribute("episode", episodeDto);
        return "adminPanel/episodeEdit";
    }

    @GetMapping("/edit/{id}")
    public String editSeason(Model model, @PathVariable UUID id) {
        EpisodeDto episodeDto = EpisodeMapper.INSTANCE.toDto(adminFacade.findEpisodeById(id));
        model.addAttribute("episode", episodeDto);
        return "adminPanel/episodeEdit";
    }

    @PostMapping("/save")
    public void saveSeason(@ModelAttribute EpisodeDto episodeDto, RedirectAttributes redirectAttributes,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirectAttributes.addFlashAttribute("message",
                "сохрание успешно" + "!");

        adminFacade.saveEpisode(EpisodeMapper.INSTANCE.toEntity(episodeDto));
//        return "redirect:/admin/content/edit/" + episodeDto.getSeason().getId();
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/delete/{id}")
    public void deleteSeason(@PathVariable UUID id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        adminFacade.deleteEpisodeById(id);
        response.sendRedirect(request.getHeader("referer"));
    }
}
