package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.exceptions.StorageException;
import ru.gbjava.kinozen.exceptions.StorageFileNotFoundException;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.entities.utils.ImageEntity;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.EpisodeService;
import ru.gbjava.kinozen.services.SeasonService;
import ru.gbjava.kinozen.services.storage.FileManager;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFacadeImpl implements AdminFacade {

    private final SeasonService seasonService;
    private final ContentService contentService;
    private final EpisodeService episodeService;

    @Value("${files.storage.video_download}")
    private Path contentImageLocation;

    private FileManager contentImageManager;

    @PostConstruct
    private void init() {
        this.contentImageManager = new FileManager(contentImageLocation);
        this.contentImageManager.init();
    }

    // todo
    @Override
    public Map<String, String> initLinks() {
        Map<String, String> links = new HashMap<>();
        links.put("content", "Content management");
        links.put("comment", "Comments");
        links.put("banner", "Banners");
        links.put("user", "Users");
        return links;
    }

    @Override
    public List<Season> getSeasonsByContent(Content content) {
        return seasonService.findSeasonByContent(content);
    }

    @Override
    public Season saveSeason(Season season) {
        return seasonService.save(season);
    }

    @Override
    public Season findSeasonById(UUID id) {
        return seasonService.findById(id);
    }

    @Override
    public void deleteSeasonById(UUID id) {
        seasonService.deleteById(id);
    }

    @Override
    public List<Content> getContentsByFilers(String name, Date releasedFrom, Date releasedTo, Boolean visible, Integer type) {
        return contentService.findAll(name, releasedFrom, releasedTo, visible, type);
    }

    @Override
    public Content saveContent(Content content, MultipartFile file) {
        content = contentService.save(content);
        if (!file.isEmpty()) {
            if (uploadImageForEntity(content, file, contentImageManager)) {
                contentService.save(content);
            } else {
                log.warn("Image not uploaded for " + content.getName());
            }
        }
        log.info("Save success: " + content.getName());
        return content;
    }

    // todo нужна проверка на тип файла, сделать интерфейс для сущностей с img, в котром указать метод set\getImageName
    private boolean uploadImageForEntity(ImageEntity imageEntity, MultipartFile file, FileManager fileManager) {
        if (!file.isEmpty()) {
            try {
                if (Objects.nonNull(imageEntity.getImageName())) {
                    fileManager.deleteFileByName(imageEntity.getImageName());
                }
                imageEntity.setImageName(fileManager.upload(file));
                return true;
            } catch (StorageException e) {
                log.error(e.getMessage());
            }
        }
        log.error("Upload image: file is empty");
        return false;
    }

    @Override
    public Content findContentById(UUID uuid) {
        return contentService.findById(uuid);
    }

    @Override
    public void deleteContentById(UUID uuid) {
        contentService.deleteById(uuid);
    }

    @Override
    public void changeVisible(UUID uuid) {
        contentService.changeVisible(uuid);
    }

    @Override
    public Resource getContentImage(String imageName) {
        try {
            return contentImageManager.loadAsResource(imageName);
        } catch (StorageFileNotFoundException e) {
            log.error("contentImageManager: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Episode findEpisodeById(UUID id) {
        return episodeService.findById(id);
    }

    @Override
    public Episode saveEpisode(Episode episode) {
        return episodeService.save(episode);
    }

    @Override
    public void deleteEpisodeById(UUID id) {
        episodeService.deleteById(id);
    }
}


