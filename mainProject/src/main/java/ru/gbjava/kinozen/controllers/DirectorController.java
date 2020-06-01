package ru.gbjava.kinozen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.services.DirectorService;


@Controller
@RequestMapping("/directors")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @GetMapping("/{id}")
    public String showDirector(Model model, @PathVariable Long id) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "director_page";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(Model model, @PathVariable Long id) {
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