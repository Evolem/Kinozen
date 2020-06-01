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

@Controller
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public String getAllGenre(Model model){
        return "genrePage";
    }

    @GetMapping("/{id}")
    public String getAllGenre(Model model, @PathVariable Long id){
        GenreDto genreDto = GenreMapper.INSTANCE.toDto(genreService.findById(id));
        model.addAttribute("genre", genreDto);

//        GenreDto genreDto = new GenreDto();
//        genreDto.setId(id);
//        genreDto.setName("dasdsad");
//        model.addAttribute("genre", genreDto);
        return "genrePage";
    }
}

