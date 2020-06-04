package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.DirectorDto;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.services.DirectorService;

import java.util.UUID;

@Controller
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    //todo переделать!

//    @GetMapping("/{id}") // поиск по url
//    public String getDirectorById(Model model, @PathVariable UUID id) {
//        Director director = directorService.findById(id);
//        model.addAttribute("director", director);
//        return "directorPage";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editProductForm(Model model, @PathVariable UUID id) {
//        Director director = directorService.findById(id);
//        model.addAttribute("director", director);
//        return "directorEdit";
//    }
//
//    @PostMapping("/edit")
//    public void add(@ModelAttribute(name = "director") DirectorDto directorDto) {
//        //todo
//    }

}