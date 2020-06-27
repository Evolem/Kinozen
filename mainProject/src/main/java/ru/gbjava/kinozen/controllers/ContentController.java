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
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.services.SubscribeService;
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
    private final SubscribeService subscribeService;

    @GetMapping
    public String getAllContent(Model model) {
        Iterable<ContentDto> dtoList = ContentMapper.INSTANCE.toDtoList(contentFacade.findAllContent());
        model.addAttribute("contentList", dtoList);
        return "contentAll";
    }

    @GetMapping("/{contentUrl}")
    public String getContentByUrl(Model model, @PathVariable String contentUrl, Principal principal) {
        Content content = contentFacade.findContentByUrl(contentUrl);
        ContentDto contentDto = ContentMapper.INSTANCE.toDto(content);
        contentFacade.checkTypeAndSetupModel(model, content);
        if (principal != null && content.getType() == TypeContent.SERIAL){
            model.addAttribute("isUserSubscribedToContent",
                    subscribeService.isUserSubscribedToContent(principal.getName(), content));
        }
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

    @PostMapping("/subscribe/{contentUrl}")
    public void subscribe(@PathVariable String contentUrl, HttpServletResponse response, HttpServletRequest request, Principal principal) throws IOException {
        subscribeService.subscribeUserToContent(principal.getName(), contentFacade.findContentByUrl(contentUrl));
        response.sendRedirect(request.getHeader("referer"));
    }
}
