package ru.gbjava.playerzen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.playerzen.services.MediaFileService;

import java.io.IOException;


@Controller
@RequestMapping("/video")
@RequiredArgsConstructor
public class MediaFileController {

    private final MediaFileService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResourceRegion> mediaSerial(@RequestHeader HttpHeaders headers, @PathVariable String id) throws IOException {
        ResourceRegion region = service.getResourceRegion(headers, id);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(region.getResource()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }
}
