package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.dto.ActorDto;
import ru.gbjava.kinozen.dto.mappers.ActorMapper;
import ru.gbjava.kinozen.services.ActorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/{url}")
    public String getGenreByUrl(Model model, @PathVariable String url) {
        ActorDto actorDto = ActorMapper.INSTANCE.toDto(actorService.findByUrl(url));
        model.addAttribute("actor", actorDto);
        return "actor";
    }

    @PostMapping /* /actor - endpoint для добавления новго актера */
    public void add(ActorDto actorDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        actorService.save(ActorMapper.INSTANCE.toEntity(actorDto));
        response.sendRedirect(request.getHeader("referer"));
    }

}
