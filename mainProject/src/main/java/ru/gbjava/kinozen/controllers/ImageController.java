package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gbjava.kinozen.services.facade.AdminFacade;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    // Возможно тут будет другой фасад, FileManager надо переделать в утилиту
    private final AdminFacade adminFacade;

    @GetMapping(value = "/content/{imageName}")
    public @ResponseBody
    Resource getContentImage(@PathVariable String imageName) {
        return adminFacade.getContentImage(imageName);
    }
}
