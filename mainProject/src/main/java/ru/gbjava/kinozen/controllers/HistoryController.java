package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.HistoryService;
import ru.gbjava.kinozen.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {

    private final UserService userService;
    private final HistoryService historyService;

    @PostMapping("/add")
    public void addHistory(@PathVariable(name = "contentId") UUID contentId, Principal principal, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final UUID userId = userService.findByLogin(principal.getName()).getId();
        historyService.save(userId, contentId);
        response.sendRedirect(request.getHeader("referer"));
    }
}
