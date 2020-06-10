package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "playerzenFeignClient", url = "${playerzen.service.url}")
public interface PlayerFeignClient {

    @GetMapping(value = "/video/{id}", produces = "application/octet-stream")
    ResponseEntity<byte[]> getContentFile(@RequestHeader HttpHeaders headers, @PathVariable String id);
}
