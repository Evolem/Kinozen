package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.exceptions.StorageException;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.SeasonService;
import ru.gbjava.kinozen.services.storage.FileStorageService;
import ru.gbjava.kinozen.services.storage.StorageService;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFacadeImpl implements AdminFacade{

    private final SeasonService seasonService;
    private final ContentService contentService;

    @Value("${files.storage.video_download}")
    private Path contentImageLocation;

    private StorageService contentImageService;

    @PostConstruct
    private void init() {
        this.contentImageService = new FileStorageService(contentImageLocation);
        this.contentImageService.init();
    }

    // todo
    public void initLinks(Model model) {
        Map<String, String> links = new HashMap<>();
        links.put("content", "Content management");
        links.put("comment", "Comments");
        links.put("banner", "Banners");
        links.put("user", "Users");
        model.addAttribute("links", links);
    }

    public Iterable<Season> getSeasonByContent(Content content) {
        return seasonService.findSeasonByContent(content);
    }

    public Season saveSeason(Season season) {
        return seasonService.save(season);
    }

    public Season findSeasonById(UUID id) {
        return seasonService.findById(id);
    }

    public void deleteSeasonById(UUID id) {
        seasonService.deleteById(id);
    }

    public List<Content> getContentsByFilers(String name, Date releasedFrom, Date releasedTo, Boolean visible, Integer type) {
        return contentService.findAll(name, releasedFrom, releasedTo, visible, type);
    }

    public Content saveContent(Content content, MultipartFile file) {
        content = contentService.save(content);
        if (!file.isEmpty()) {
            if (!uploadImageForContent(content, file, contentImageService)) {
                log.error("Image not uploaded for " + content.getName());
            }
        }
        log.info("Save success: " + content.getName());
        return content;
    }

    // todo нужна проверка на тип файла
    private boolean uploadImageForContent(Content content, MultipartFile file, StorageService storageService) {
        if (!file.isEmpty()) {
            try {
                if (!Objects.isNull(content.getImageName())) {
                    storageService.deleteFileByName(content.getImageName());
                }
                content.setImageName(storageService.store(file));
                contentService.save(content);
                return true;
            } catch (StorageException e) {
                log.error(e.getMessage());
            }
        }
        log.warn("Upload image: file is empty");
        return false;
    }

    public Content findContentById(UUID uuid) {
        return contentService.findById(uuid);
    }

    public void deleteContentById(UUID uuid) {
        contentService.deleteById(uuid);
    }

    public void changeVisible(UUID uuid) {
        contentService.changeVisible(uuid);
    }
}


