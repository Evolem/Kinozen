package ru.gbjava.kinozen.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.dto.mappers.CommentMapper;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.EpisodeMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.facade.ContentFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
@Api("Набор методов для Контента.")
public class ContentController {

    private final ContentFacade contentFacade;

    @ApiOperation(value = "Получить страницу со всеми сериалами.", response = String.class)
    @GetMapping(value = "/serials")
    public String getAllSerial(Model model, @RequestParam(required = false) UUID genre) {
        contentFacade.modelSetupForSerials(model, genre);
        return "contentAll";
    }

    @ApiOperation(value = "Получить страницу со всеми фильмами.", response = String.class)
    @GetMapping(value = "/films")
    public String getAllFilms(Model model, @RequestParam(required = false) UUID genre) {
        contentFacade.modelSetupForFilms(model, genre);
        return "contentAll";
    }

    @ApiOperation(value = "Получить страницу со определёным контентом по id.", response = String.class)
    @GetMapping("/{contentUrl}")
    public String getContentByUrl(Model model, Principal principal, @PathVariable String contentUrl) {
        Content content = contentFacade.findContentByUrl(contentUrl);
        contentFacade.checkWished(model, content);
        contentFacade.checkTypeAndSetupModel(model, content);
        contentFacade.updateHistory(principal, content);
        return "contentPage";
    }


    //todo большой метод стал, возможно стоит его оптимизировать
    @GetMapping("/{contentUrl}/{seasonUrl}")
    public String getSeasonByUrl(Model model,
                                 Principal principal,
                                 @PathVariable String contentUrl,
                                 @PathVariable String seasonUrl,
                                 @RequestParam(required = false) Integer episode) {

        Content content = contentFacade.findContentByUrl(contentUrl);
        contentFacade.updateHistory(principal, content);
        List<Season> seasons = contentFacade.findAllSeasonByContent(content);
        Season currentSeason = contentFacade.findSeasonByContentAndUrl(content, seasonUrl);
        List<Episode> episodes = currentSeason.getEpisodes();
        EpisodeDto currentEpisode = EpisodeMapper.INSTANCE.toDto(contentFacade.getEpisodeFromListByNumber(episodes, episode));
        List<Comment> comments = contentFacade.findAllCommentByIdEntity(currentEpisode.getId()); //Ищем комментарии

        model.addAttribute("idEntity", currentEpisode.getId());
        model.addAttribute("description", currentEpisode.getDescription());
        model.addAttribute("episodes", EpisodeMapper.INSTANCE.toDtoList(episodes));
        model.addAttribute("currentEpisode", currentEpisode);
        model.addAttribute("seasons", SeasonMapper.INSTANCE.toDtoList(seasons));
        model.addAttribute("currentSeason", SeasonMapper.INSTANCE.toDto(currentSeason));
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        model.addAttribute("comments", CommentMapper.INSTANCE.toDtoList(comments)); //отправляем на страничку список DTO comment
        contentFacade.checkWished(model, content);
        return "contentPage";
    }

    @ApiOperation(value = "Отправка лайка", response = String.class)
    @PostMapping("/like/{contentUrl}")
    public void likeContent(@PathVariable String contentUrl, HttpServletResponse response, HttpServletRequest request, Principal principal) throws IOException {
        contentFacade.likeContentByUser(principal.getName(), contentUrl);
        response.sendRedirect(request.getHeader("referer"));
    }

    @ApiOperation(value = "Отправка дизлайка", response = String.class)
    @PostMapping("/dislike/{contentUrl}")
    public void dislikeContent(@PathVariable String contentUrl, HttpServletResponse response, HttpServletRequest request, Principal principal) throws IOException {
        contentFacade.dislikeContentByUser(principal.getName(), contentUrl);
        response.sendRedirect(request.getHeader("referer"));
    }
}
