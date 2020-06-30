package ru.gbjava.kinozen.services.facade;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AdminFacade {

    Map<String, String> initLinks();

    List<Season> getSeasonsByContent(Content content);

    Season saveSeason(Season season);

    Season findSeasonById(UUID id);

    void deleteSeasonById(UUID id);

    List<Content> getContentsByFilers(String name, Date releasedFrom, Date releasedTo, Boolean visible, Integer type);

    Content saveContent(Content content, MultipartFile file);

    Content findContentById(UUID uuid);

    void deleteContentById(UUID uuid);

    void changeVisible(UUID uuid);

    Resource getContentImage(String imageName);

    Episode findEpisodeById(UUID id);

    Episode saveEpisode(Episode episode);

    void deleteEpisodeById(UUID id);
}
