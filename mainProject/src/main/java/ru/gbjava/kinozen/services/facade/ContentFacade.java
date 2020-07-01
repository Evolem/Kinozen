package ru.gbjava.kinozen.services.facade;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface ContentFacade {
    List<Content> findAllContent();

    Content findContentByUrl(String url);

    void saveContent(Content content);

    void deleteContentById(UUID uuid);

    List<Season> findAllSeasonByContent(Content content);

    Season findSeasonByContentAndUrl(Content content, String url);

    List<Episode> findAllEpisodeBySeason(Season season);

    Episode getEpisodeFromListByNumber(List<Episode> episodes, Integer episodeNumber);

    void checkTypeAndSetupModel(Model model, Content content);

    ResponseEntity<byte[]> getContentFile(HttpHeaders headers, String uuid);

    void uploadContentFile(MultipartFile file, String uuid);

    void likeContentByUser(String login, String contentUrl);

    void checkWished(Model model, Content content);

    List<Content> findAllSerials();

    List<Content> findAllFilms();

    void modelSetupForFilms(Model model, UUID genre);

    void modelSetupForSerials(Model model, UUID genre);


    void dislikeContentByUser(String login, String contentUrl);

    List<Content> findMostPopularContent();

    List<Comment> findAllCommentByIdEntity(UUID id);

    void saveComment(Comment comment);

    void updateHistory(Principal principal, Content content);
}
