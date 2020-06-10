package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.feign.clients.PlayerFeignClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerFeignClient playerFeignClient;

    @GetMapping(value = "video/{id}")
    public ResponseEntity<byte[]> getContentFile(@RequestHeader HttpHeaders headers, @PathVariable String id) {
        return playerFeignClient.getContentFile(headers, id);
    }
}
