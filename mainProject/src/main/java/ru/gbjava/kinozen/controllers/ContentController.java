package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.EpisodeDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.EpisodeMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.facade.ContentFacade;
import ru.gbjava.kinozen.services.feign.clients.PlayerFeignClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/content")
@RequiredArgsConstructor
public class ContentController {

    private final ContentFacade contentFacade;
    private final PlayerFeignClient playerFeignClient;
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
        Iterable<Season> seasons = contentFacade.findAllSeasonByContent(content);

        model.addAttribute("idEntity", content.getId());
        model.addAttribute("seasons", seasons);
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        return "contentPage";
    }

    @GetMapping("/{contentUrl}/{seasonUrl}")
    public String getSeasonByUrl(Model model,
                                 @PathVariable String contentUrl,
                                 @PathVariable String seasonUrl,
                                 @RequestParam(required = false) Integer episode) {

        Content content = contentFacade.findContentByUrl(contentUrl);
        Iterable<Season> seasons = contentFacade.findAllSeasonByContent(content);
        Season currentSeason = contentFacade.findSeasonByContentAndUrl(content, seasonUrl);
        List<Episode> episodes = currentSeason.getEpisodes();
        EpisodeDto episodeDto = EpisodeMapper.INSTANCE.toDto(contentFacade.getEpisodeFromListByNumber(episodes, episode));

        model.addAttribute("idEntity", episodeDto.getId());
        model.addAttribute("episodes", episodes);
        model.addAttribute("currentSeason", currentSeason);
        model.addAttribute("seasons", seasons);
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        return "contentPage";
    }

    @GetMapping(value = "video/{id}")
    public ResponseEntity<byte[]> mediaSerial(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        return playerFeignClient.mediaSerial(headers, id);
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
