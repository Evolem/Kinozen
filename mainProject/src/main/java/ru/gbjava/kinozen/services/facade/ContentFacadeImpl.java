package ru.gbjava.kinozen.services.facade;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.EpisodeService;
import ru.gbjava.kinozen.services.SeasonService;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.feign.clients.PlayerFeignClient;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentFacadeImpl implements ContentFacade {

    private final ContentService contentService;
    private final SeasonService seasonService;
    private final EpisodeService episodeService;
    private final PlayerFeignClient playerFeignClient;
    private final UserService userService;

    private final int LIMIT_POPULARITY = 60;

    @Override
    public List<Content> findAllContent() {
        return contentService.findAll();
    }

    @Override
    public Content findContentByUrl(String url) {
        return contentService.findByUrl(url);
    }

    @Override
    public void saveContent(Content content) {
        contentService.save(content);
    }

    @Override
    public void deleteContentById(UUID uuid) {
        contentService.deleteById(uuid);
    }

    @Override
    public List<Season> findAllSeasonByContent(Content content) {
        return seasonService.findSeasonByContent(content);
    }

    @Override
    public Season findSeasonByContentAndUrl(Content content, String url) {
        return seasonService.findByContentAndUrl(content, url);
    }

    @Override
    public List<Episode> findAllEpisodeBySeason(Season season) {
        return episodeService.findAllBySeason(season);
    }

    //todo переделать исключение
    @Override
    public Episode getEpisodeFromListByNumber(List<Episode> episodes, Integer episodeNumber) throws RuntimeException {

        if (Objects.isNull(episodeNumber)) {
            for (Episode e : episodes) {
                if (e.getNumberEpisode() == 1) {
                    return e;
                }
            }
        } else {
            for (Episode e : episodes) {
                if (e.getNumberEpisode().equals(episodeNumber)) {
                    return e;
                }
            }
        }
        throw new RuntimeException("Episode not found!");
    }

    @Override
    public void checkTypeAndSetupModel(Model model, Content content) {
        if (content.getType().ordinal() == 0) {
            List<Season> seasons = findAllSeasonByContent(content);
            model.addAttribute("seasons", SeasonMapper.INSTANCE.toDtoList(seasons));
        } else {
            model.addAttribute("idEntity", content.getId());
        }
        model.addAttribute("content", ContentMapper.INSTANCE.toDto(content));
        model.addAttribute("description", content.getDescription());
    }

    @Override
    public ResponseEntity<byte[]> getContentFile(HttpHeaders headers, String uuid) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            responseEntity = playerFeignClient.getContentFile(headers, uuid);
        } catch (RetryableException e) {
            log.error(e.getMessage());
        }
        return responseEntity;
    }

    @Override
    public void uploadContentFile(MultipartFile file, String uuid) {
        playerFeignClient.uploadContentFile(file, uuid);
    }

    @Override
    public void likeContentByUser(String login, String contentUrl) {
        User user = userService.findByLogin(login);
        Content content = findContentByUrl(contentUrl);
        Set<Content> likedContent = user.getLikedContent();
        Set<Content> dislikedContent = user.getDislikedContent();

        if (likedContent.contains(content)) {
            likedContent.remove(content);
        } else {
            likedContent.add(content);
        }

        dislikedContent.remove(content);
        userService.save(user);
    }

    @Override
    public void dislikeContentByUser(String login, String contentUrl) {
        User user = userService.findByLogin(login);
        Content content = findContentByUrl(contentUrl);
        Set<Content> likedContent = user.getLikedContent();
        Set<Content> dislikedContent = user.getDislikedContent();

        if (dislikedContent.contains(content)) {
            dislikedContent.remove(content);
        } else {
            dislikedContent.add(content);
        }

        likedContent.remove(content);
        userService.save(user);
    }

    @Override
    public List<Content> findMostPopularContent() {
        List<Content> contentList = contentService.findAll();
        return contentList.stream()
                .filter(content -> calculateRating(content.getLikes().size(), content.getDislikes().size()) > LIMIT_POPULARITY)
                .sorted((c1, c2) -> calculateRating(c2.getLikes().size(), c2.getDislikes().size())
                        .compareTo(calculateRating(c1.getLikes().size(), c1.getDislikes().size())))
                .limit(5)
                .collect(Collectors.toList());
    }

    private Integer calculateRating(double likes, double dislikes) {
        if (likes == 0) {
            return 0;
        }
        return (int) (likes / (likes + dislikes) * 100);
    }
}
