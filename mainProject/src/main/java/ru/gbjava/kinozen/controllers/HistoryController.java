package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.UserService;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final UserService userService;

    @PostMapping("/add")
    public void addHistory(@PathVariable(name = "contentId") UUID contentId, Principal principal){
        final UUID userId = userService.findByLogin(principal.getName()).getId();
    }




}
