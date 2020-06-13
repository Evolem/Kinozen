package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "playerzenFeignClient", url = "${playerzen.service.url}")
public interface PlayerFeignClient {

    @GetMapping(value = "/video/{uuid}", produces = "application/octet-stream")
    ResponseEntity<byte[]> getContentFile(@RequestHeader HttpHeaders headers, @PathVariable String uuid);

    @PostMapping(value = "/video/{uuid}", consumes = "multipart/form-data")
    void uploadContentFile(@RequestParam("file") MultipartFile file, @PathVariable String uuid);
}
