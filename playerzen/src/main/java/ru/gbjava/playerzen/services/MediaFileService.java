package ru.gbjava.playerzen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;
import ru.gbjava.playerzen.persistance.entities.MediaFile;
import ru.gbjava.playerzen.persistance.repositories.MediaFileRepository;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static java.lang.Math.min;

@Service
@RequiredArgsConstructor
public class MediaFileService {

    private final MediaFileRepository repository;

    @Value("${files.storepath.storage}")
    private String mainPath;

    public MediaFile getMediaFile(String name) {
        return  repository.findByName(name);
    }

    public ResourceRegion getResourceRegion(HttpHeaders headers, String... name) throws MalformedURLException {
        UrlResource resource;
        if (name.length < 3) {
            resource = new UrlResource(String.format("file:%s/%s/%s.mp4", mainPath, name[0], name[1]));
        } else {
            resource = new UrlResource(String.format("file:%s/%s/%s/%s.mp4", mainPath, name[0], name[1], name[2]));
        }
        ResourceRegion region = null;
        try {
            region = resourceRegion(resource, headers);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return region;
    }


    private ResourceRegion resourceRegion(UrlResource resource, HttpHeaders headers) throws IOException {
        long contentLength = resource.contentLength();
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
}
