package ru.gbjava.kinozen.services.facade;

import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;

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
}
