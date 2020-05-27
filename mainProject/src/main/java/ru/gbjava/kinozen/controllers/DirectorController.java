package ru.gbjava.kinozen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.services.DirectorService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/directors")
public class DirectorController {
    private DirectorService directorService;
//    private MediaService mediaService;


    @GetMapping("/{id}")
    public String showProduct(Model model, Principal principal, @PathVariable Long id) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "director_page";
    }

}