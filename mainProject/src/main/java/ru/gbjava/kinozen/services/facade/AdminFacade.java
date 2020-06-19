package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.exceptions.StorageException;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.SeasonService;
import ru.gbjava.kinozen.services.storage.FileSystemStorageService;
import ru.gbjava.kinozen.services.storage.StorageService;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminFacade {

    private final SeasonService seasonService;
    private final ContentService contentService;

    @Value("${files.storage.video_download}")
    private Path contentImageLocation;

    private StorageService contentImageService;

    @PostConstruct
    private void init() {
        this.contentImageService = new FileSystemStorageService(contentImageLocation);
        this.contentImageService.init();
    }

    // todo
    public void initLinks(Model model) {
        Map<String, String> links = new TreeMap<>();
        links.put("content", "Content management");
        links.put("comments", "Comments");
        links.put("banners", "Banners");
        links.put("users", "Users");
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
        return uploadImageContent(content, file, contentImageService);
    }

    // private
    private Content uploadImageContent(Content content, MultipartFile file, StorageService storageService) {
        if (!Objects.isNull(file)) {
            try {
                if (!content.getImageName().isEmpty()) {
                    storageService.deleteByMame(content.getImageName());
                }
                content.setImageName(storageService.store(file));
                return contentService.save(content);
            } catch (StorageException e) {
                log.error(e.getMessage());
            }
        }
        throw new StorageException("MultipartFile is null");
    }
}


