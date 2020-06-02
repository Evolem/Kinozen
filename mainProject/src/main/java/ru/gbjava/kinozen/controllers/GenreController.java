package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.dto.GenreDto;
import ru.gbjava.kinozen.dto.mappers.GenreMapper;
import ru.gbjava.kinozen.services.GenreService;

import java.util.List;

@Controller
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public String getAllGenre(Model model){
        Iterable<GenreDto> genres = GenreMapper.INSTANCE.toDtoList(genreService.findAll());
        model.addAttribute("genres", genres);
        return "genreAll";
    }

    @GetMapping("/{id}")
    public String getAllGenre(Model model, @PathVariable Long id){
        GenreDto genreDto = GenreMapper.INSTANCE.toDto(genreService.findById(id));
        model.addAttribute("genre", genreDto);
        return "genrePage";
    }
}

