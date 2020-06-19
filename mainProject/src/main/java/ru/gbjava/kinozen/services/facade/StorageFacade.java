package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.exceptions.StorageException;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.storage.FileSystemStorageService;
import ru.gbjava.kinozen.services.storage.StorageService;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageFacade {

    @Value("${files.storage.video_download}")
    private Path contentImageLocation;

    @Value("${files.storage.video_download}")
    private Path bannerImageLocation;

    @Value("${files.storage.video_download}")
    private Path actorImageLocation;

    @Value("${files.storage.video_download}")
    private Path directorImageLocation;

    private StorageService contentImageService;
    private StorageService bannerImageService;
    private StorageService actorImageService;
    private StorageService directorImageService;

    private final ContentService contentService;


    @PostConstruct
    private void init() {
        this.contentImageService = new FileSystemStorageService(contentImageLocation);
        this.bannerImageService = new FileSystemStorageService(bannerImageLocation);
        this.actorImageService = new FileSystemStorageService(actorImageLocation);
        this.directorImageService = new FileSystemStorageService(directorImageLocation);

        this.contentImageService.init();
        this.bannerImageService.init();
        this.actorImageService.init();
        this.directorImageService.init();
    }

    public boolean uploadImageContent(MultipartFile file, Content content) {
        if (!Objects.isNull(file)) {
            try {
                content.setImageName(contentImageService.store(file));
                contentService.save(content);
                return true;
            } catch (StorageException e) {
                log.error(e.getMessage());
            }
        }
        return false;
    }


}
