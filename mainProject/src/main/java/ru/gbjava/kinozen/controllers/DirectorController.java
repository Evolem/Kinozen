package ru.gbjava.kinozen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.services.DirectorService;

import java.util.List;


@Controller
@RequestMapping("/directors")
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    private DirectorService mediaService;

    @GetMapping("/{id}")
    public String showDirector(Model model, @PathVariable Long id) {
        Director director = directorService.findById(id);
        model.addAttribute("director", director);
        return "director_page";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(Model model, @PathVariable Long id) {
        Director director = directorService.findById(id);
        List<Media> media = mediaService.getAll();
        model.addAttribute("product", director);
        model.addAttribute("categories", media);
        return "edit_director";
    }

    @PostMapping("/edit")
    public String saveProduct(@ModelAttribute(name = "product") Director director) {
        directorService.save(director);
        return "redirect:/";
    }

}