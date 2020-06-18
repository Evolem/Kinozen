package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.EpisodeMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.facade.ContentFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentFacade contentFacade;

    @GetMapping
    public String getAllContent(Model model) {
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        model.addAttribute("contentList", dtoList);
        return "contentAll";
    }

    @GetMapping("/{contentUrl}")
    public String getContentByUrl(Model model, @PathVariable String contentUrl) {
        Content content = contentFacade.findContentByUrl(contentUrl);
        contentFacade.checkTypeAndSetupModel(model, content);
        return "contentPage";
    }


    @GetMapping("/{contentUrl}/{seasonUrl}")
    public String getSeasonByUrl(Model model,
                                 @PathVariable String contentUrl,
                                 @PathVariable String seasonUrl,
                                 @RequestParam(required = false) Integer episode) {

        Content content = contentFacade.findContentByUrl(contentUrl);
        List<Season> seasons = contentFacade.findAllSeasonByContent(content);
        Season currentSeason = contentFacade.findSeasonByContentAndUrl(content, seasonUrl);
        List<Episode> episodes = currentSeason.getEpisodes();
        EpisodeDto currentEpisode = EpisodeMapper.INSTANCE.toDto(contentFacade.getEpisodeFromListByNumber(episodes, episode));

        model.addAttribute("idEntity", currentEpisode.getId());
        model.addAttribute("description", currentEpisode.getDescription());
        model.addAttribute("episodes", EpisodeMapper.INSTANCE.toDtoList(episodes));
        model.addAttribute("currentEpisode", currentEpisode);
        model.addAttribute("seasons", SeasonMapper.INSTANCE.toDtoList(seasons));
        model.addAttribute("currentSeason", SeasonMapper.INSTANCE.toDto(currentSeason));
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        return "contentPage";
    }

    @PostMapping("/like/{contentUrl}")
    public void likeContent(@PathVariable String contentUrl, HttpServletResponse response, HttpServletRequest request, Principal principal) throws IOException {
        contentFacade.likeContentByUser(principal.getName(), contentUrl);
        response.sendRedirect(request.getHeader("referer"));
    }
}
