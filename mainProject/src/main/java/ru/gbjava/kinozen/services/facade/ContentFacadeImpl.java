package ru.gbjava.kinozen.services.facade;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.CommentMapper;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.dto.mappers.SeasonMapper;
import ru.gbjava.kinozen.persistence.entities.*;
import ru.gbjava.kinozen.services.*;
import ru.gbjava.kinozen.services.feign.clients.PlayerFeignClient;
import ru.gbjava.kinozen.services.wishlist.WishListService;

import java.security.Principal;
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
    private final WishListService wishListService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final HistoryService historyService;

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

            Iterable<ContentDto> popularContent = ContentMapper.INSTANCE.toDtoList(findMostPopularContent());
            model.addAttribute("popularContentList", popularContent);
        } else {
            model.addAttribute("idEntity", content.getId());
            List<Comment> comments = findAllCommentByIdEntity(content.getId()); //Ищем комментарии
            model.addAttribute("comments", CommentMapper.INSTANCE.toDtoList(comments)); //отправляем на страничку список DTO comment
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
    public void checkWished(Model model, Content content) {
        model.addAttribute("isWished", wishListService.isWished(content));
    }

    @Override
    public List<Content> findAllSerials() {
        return contentService.findAllSerials();
    }

    @Override
    public List<Content> findAllFilms() {
        return contentService.findAllFilms();
    }

    //todo переделать в один метод
    @Override
    public void modelSetupForFilms(Model model, UUID idGenre) {
        if (Objects.isNull(idGenre)) {
            model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(findAllFilms()));
        } else {
            Genre genre = genreService.findById(idGenre);
            model.addAttribute("selectedGenre", genre.getName());
            model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(contentService.findAllFilmsByGenre(genre)));
        }
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("typeName", "Фильмы");
    }

    //todo переделать в один метод
    @Override
    public void modelSetupForSerials(Model model, UUID idGenre) {
        if (Objects.isNull(idGenre)) {
            model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(findAllSerials()));
        } else {
            Genre genre = genreService.findById(idGenre);
            model.addAttribute("selectedGenre", genre.getName());
            model.addAttribute("contentList", ContentMapper.INSTANCE.toDtoList(contentService.findAllSerialsByGenre(genre)));
        }
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("typeName", "Сериалы");
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

    @Override
    public List<Comment> findAllCommentByIdEntity(UUID id) {
        return commentService.findAllCommentByIdEntity(id);
    }

    @Override
    public void saveComment(Comment comment) {

        if (comment.getText().trim().length() != 0) //проверка на пустоту строки
            commentService.save(comment);
    }

    @Override
    public void updateHistory(Principal principal, Content content) {
        if (Objects.nonNull(principal)) {
            historyService.save(userService.findByLogin(principal.getName()).getId(), content.getId());
        }

    }

    private Integer calculateRating(double likes, double dislikes) {
        if (likes == 0) {
            return 0;
        }
        return (int) (likes / (likes + dislikes) * 100);
    }
}
