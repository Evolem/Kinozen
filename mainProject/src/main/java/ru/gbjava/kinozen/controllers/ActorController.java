package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.dto.mappers.ActorMapper;
import ru.gbjava.kinozen.services.ActorService;
import ru.gbjava.kinozen.dto.ActorDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    //todo поправить логику

    @GetMapping("/{id}")
    public String getActorById(Model model, @PathVariable UUID id){
        model.addAttribute("actor", actorService.findById(id));
        return "actor";
    }

    @GetMapping("")
    public String addActor(){
        return "addActor";
    }

    @PostMapping /* /actor - endpoint для добавления новго актера */
    public void addActor(ActorDto actorDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        actorService.save(ActorMapper.INSTANCE.toEntity(actorDto));
        response.sendRedirect(request.getHeader("referer"));
    }



}
