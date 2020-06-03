package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.services.DirectorService;

import java.util.UUID;

@Controller
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/{id}")
    public String showDirector(Model model, @PathVariable UUID id) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "director_page";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(Model model, @PathVariable UUID id) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "director_edit";
    }

    @PostMapping("/edit")
    public String saveProduct(@ModelAttribute(name = "director") Director director) {
        directorService.save(director);
        return "redirect:/";
    }

}