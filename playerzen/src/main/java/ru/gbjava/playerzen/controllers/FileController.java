package ru.gbjava.playerzen.controllers;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.min;

@Controller
public class FileController {

    @GetMapping(value = "/video/{name}")
    public ResponseEntity<ResourceRegion> video(@RequestHeader HttpHeaders headers, @PathVariable String name) throws IOException {
        UrlResource resource = new UrlResource("file:D:\\Down\\Love.Death.&.Robots.S01E02.1080p.NF.WEB-DL.Rus.Eng_THD.mp4.mp4");
        ResourceRegion region = resourceRegion(resource, headers);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
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
