package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.dto.mappers.CommentMapper;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.EpisodeMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.*;
import ru.gbjava.kinozen.services.facade.CommentFacade;
import ru.gbjava.kinozen.services.facade.ContentFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentFacade contentFacade;
    private final CommentFacade commentFacade;     //добавил обработку комментариев

    //todo поправить логику добавления!

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

        List<Comment> comments = commentFacade.findAllCommentByEpisodeId(currentEpisode.getId()); //Ищем комментарии

        model.addAttribute("idEntity", currentEpisode.getId());
        model.addAttribute("description", currentEpisode.getDescription());
        model.addAttribute("episodes", EpisodeMapper.INSTANCE.toDtoList(episodes));
        model.addAttribute("currentSeason", SeasonMapper.INSTANCE.toDto(currentSeason));
        model.addAttribute("seasons", SeasonMapper.INSTANCE.toDtoList(seasons));
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        model.addAttribute("comment", CommentMapper.INSTANCE.toDtoList(comments)); //отправляем на страничку список DTO comment
        return "contentPage";
    }


    @GetMapping("/add")
    public String add(Model model) {
        ContentDto contentDto = new ContentDto();
        model.addAttribute("content", contentDto);
        return "contentEdit";
    }

    @PostMapping("/add")
    public void add(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentFacade.saveContent(ContentMapper.INSTANCE.toEntity(contentDto));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/delete")
    public void deleteContent(ContentDto contentDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
        contentFacade.deleteContentById(contentDto.getId());
        response.sendRedirect(request.getHeader("referer"));
    }


}
