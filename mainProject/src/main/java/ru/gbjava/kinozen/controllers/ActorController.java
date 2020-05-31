package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.ActorService;
import ru.gbjava.kinozen.services.pojo.ActorPojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/{id}")
    public String getActorById(Model model, @PathVariable int id){
        model.addAttribute("actor", actorService.getActorById(id));
        return "actor";
    }

    @PostMapping /* /actor - endpoint для добавления новго актера */
    public void addActor(ActorPojo actorPojo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        actorService.save(actorPojo);
        response.sendRedirect(request.getHeader("referer"));
    }

}
