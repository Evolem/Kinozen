package ru.gbjava.playerzen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import ru.gbjava.playerzen.exceptions.EntityNotFoundException;
import ru.gbjava.playerzen.persistance.entities.MediaFile;
import ru.gbjava.playerzen.persistance.repositories.MediaFileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.min;

@Slf4j
@Service
@RequiredArgsConstructor
public class MediaFileService {

    private final MediaFileRepository repository;

    private MediaFile mediaFile;

    @Value("${files.storepath.storage}")
    private String mainPath;

    public MediaFile getMediaFile(String name) throws EntityNotFoundException {
        return  repository.findByMedia(UUID.fromString(name)).orElseThrow(() -> new EntityNotFoundException("File not found: " + name));
    }

    public ResourceRegion getResourceRegion(HttpHeaders headers, String media) throws MalformedURLException, EntityNotFoundException {
        checkMediaFile(media);

        UrlResource resource = new UrlResource(String.format("file:%s/%s.mp4", mainPath, mediaFile.getNameFile()));

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

    private void checkMediaFile(String media) throws EntityNotFoundException {
        if (mediaFile == null || !mediaFile.getMedia().equals(UUID.fromString(media))) {
            mediaFile = repository.findByMedia(UUID.fromString(media)).orElseThrow(() -> new EntityNotFoundException("File not found: " + media));
        }
    }
}
