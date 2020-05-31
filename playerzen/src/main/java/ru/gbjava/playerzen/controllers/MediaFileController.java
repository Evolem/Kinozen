package ru.gbjava.playerzen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.playerzen.persistance.entities.MediaFile;
import ru.gbjava.playerzen.services.MediaFileService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static java.lang.Math.min;

@Controller
@RequestMapping("/video")
@RequiredArgsConstructor
public class MediaFileController {

    private final MediaFileService service;

    @GetMapping(value = "/serial/{id}")
    public ResponseEntity<ResourceRegion> mediaSerial(@RequestHeader HttpHeaders headers, @PathVariable String id) throws IOException {
        ResourceRegion region = service.getResourceRegion(headers, id);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(region);
    }

    @GetMapping(value = "/film/{id}")
    public ResponseEntity<ResourceRegion> mediaMove(@RequestHeader HttpHeaders headers, @PathVariable String id) throws IOException {
        ResourceRegion region = service.getResourceRegion(headers, id);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(region);
    }

//    @GetMapping(value = "/video/{name}")
//    public ResponseEntity<ResourceRegion> video(@RequestHeader HttpHeaders headers, @PathVariable String name) throws IOException {
//        UrlResource resource = new UrlResource("file:D:\\Down\\Love.Death.&.Robots.S01E02.1080p.NF.WEB-DL.Rus.Eng_THD.mp4.mp4");
//        ResourceRegion region = resourceRegion(resource, headers);
//        return ResponseEntity
//                .status(HttpStatus.PARTIAL_CONTENT)
//                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
//                .body(region);
//    }
}
