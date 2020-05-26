package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

    @GetMapping
    public String getMediaContent(Model model){
        return "media";
    }
}
