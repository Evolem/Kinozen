package ru.gbjava.playerzen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.gbjava.playerzen.exceptions.EntityNotFoundException;
import ru.gbjava.playerzen.services.ContentFileService;

import java.io.IOException;


@Controller
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

    @GetMapping("/")
    public String page() {
        return "page";
    }

    @PostMapping(value = "/{uuid}")
    public void uploadContentFile(@RequestParam("file") MultipartFile file, @PathVariable String uuid) throws IOException {
        service.uploadContentFile(file, uuid);
    }
}
