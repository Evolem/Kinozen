package ru.gbjava.playerzen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.playerzen.exceptions.EntityNotFoundException;
import ru.gbjava.playerzen.services.ContentFileService;

import java.io.IOException;


@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class ContentFileController {

    private final ContentFileService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResourceRegion> getContentFile(@RequestHeader HttpHeaders headers, @PathVariable String id) throws IOException, EntityNotFoundException {
        ResourceRegion region = service.getResourceRegion(headers, id);
        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(region.getResource()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    @PostMapping(value = "/upload")
    public void uploadContentFile(@RequestParam("video") MultipartFile video, @RequestParam("id") String id) throws IOException {
        service.uploadContentFile(video, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteContentFile(@PathVariable String id) {
        service.deleteContentFile(id);
    }
}
