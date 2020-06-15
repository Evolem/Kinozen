package ru.gbjava.playerzen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.playerzen.exceptions.EntityNotFoundException;
import ru.gbjava.playerzen.persistance.entities.ContentFile;
import ru.gbjava.playerzen.persistance.repositories.ContentFileRepository;
import ru.gbjava.playerzen.utilites.FileNameGenerator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.min;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentFileService {

    private final ContentFileRepository repository;

    private ContentFile contentFile;

    @Value("${files.storepath.storage}")
    private Path STORAGE;

    public ContentFile getContentFile(String name) throws EntityNotFoundException {
        return  repository.findByContent(UUID.fromString(name)).orElseThrow(() -> new EntityNotFoundException("File not found: " + name));
    }

    @Transactional
    public void uploadContentFile(MultipartFile file, String uuidContent) {

        if (repository.findByContent(UUID.fromString(uuidContent)).isPresent()) {
            log.error("Content {} already exists", uuidContent);
            return;
        }

        try {
            if (file.getBytes().length != 0) {
                String uploadedFileName = FileNameGenerator.generate(STORAGE) + ".mp4";
                Path targetLocation = STORAGE.resolve(uploadedFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                ContentFile contentFile = ContentFile.builder()
                        .content(UUID.fromString(uuidContent))
                        .nameFile(uploadedFileName)
                        .build();
                repository.save(contentFile);

                log.info("File {}, UUID: {} has been successfully uploaded!", uploadedFileName, uuidContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ResourceRegion getResourceRegion(HttpHeaders headers, String content) throws MalformedURLException, EntityNotFoundException {
        checkContentFile(content);

        UrlResource resource = new UrlResource(String.format("file:%s/%s", STORAGE, contentFile.getNameFile()));

        return resourceRegion(resource, headers);
    }

    private ResourceRegion resourceRegion(UrlResource resource, HttpHeaders headers) {
        long contentLength = 0;
        try {
            contentLength = resource.contentLength();
        } catch (IOException e) {
            log.error("File {} is not valid!", resource.getFilename());
            throw new RuntimeException(e.getMessage());
        }

        List<HttpRange> list = headers.getRange();
        HttpRange range;

        if (list.isEmpty()) {
            range = null;
        } else {
            range = list.get(0);
        }

        if (range != null) {
            long start = range.getRangeStart(contentLength);
            long end = range.getRangeEnd(contentLength);
            long rangerLength = min(1024 * 1024, end - start + 1);
            return new ResourceRegion(resource, start, rangerLength);
        } else {
            long rangerLength = min(1024 * 1024, contentLength);
            return new ResourceRegion(resource, 0, rangerLength);
        }

    }

    private void checkContentFile(String content) throws EntityNotFoundException {
        if (contentFile == null || !contentFile.getContent().equals(UUID.fromString(content))) {
            contentFile = repository.findByContent(UUID.fromString(content)).orElseThrow(() -> new EntityNotFoundException("File not found: " + content));
        }
    }
}
