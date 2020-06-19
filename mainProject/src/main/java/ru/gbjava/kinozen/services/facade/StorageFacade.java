package ru.gbjava.kinozen.services.facade;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.storage.FileSystemStorageService;
import ru.gbjava.kinozen.services.storage.StorageService;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class StorageFacade {

    @Value("${files.storage.video_download}")
    private Path contentImageLocation;

//    @Value("${files.storage.video_download}")
//    private Path bannerImageLocation;
//
//    @Value("${files.storage.video_download}")
//    private Path actorImageLocation;
//
//    @Value("${files.storage.video_download}")
//    private Path directorImageLocation;

    private StorageService contentImageService;
//    private FileSystemStorageService bannerImageService;
//    private FileSystemStorageService actorImageService;
//    private FileSystemStorageService directorImageService;

    private final ContentService contentService;


    @PostConstruct
    private void init() {
        this.contentImageService = new FileSystemStorageService(contentImageLocation);
//        this.bannerImageService = new FileSystemStorageService(bannerImageLocation);
//        this.actorImageService = new FileSystemStorageService(actorImageLocation);
//        this.directorImageService = new FileSystemStorageService(directorImageLocation);

//        this.contentImageService.init();
//        this.bannerImageService.init();
//        this.actorImageService.init();
//        this.directorImageService.init();

    }


    public void uploadImageContent(MultipartFile file, Content content) {
       String fileName = contentImageService.store(file);
//        content.setImageName(fileName);
      //  contentService.save(content);
    }


}
