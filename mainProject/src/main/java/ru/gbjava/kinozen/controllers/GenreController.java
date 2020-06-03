package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.GenreDto;
import ru.gbjava.kinozen.dto.mappers.GenreMapper;
import ru.gbjava.kinozen.services.GenreService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    //todo поправить логику добавления!

    @GetMapping
    public String getAllGenre(Model model) {
        Iterable<GenreDto> genres = GenreMapper.INSTANCE.toDtoList(genreService.findAll());
        model.addAttribute("genres", genres);
        return "genreAll";
    }

    @GetMapping("/{url}")
    public String getGenreByUrl(Model model, @PathVariable String url) {
        GenreDto genreDto = GenreMapper.INSTANCE.toDto(genreService.findByUrl(url));
        model.addAttribute("genre", genreDto);
        return "genrePage";
    }

    @PostMapping
    public void addGenre(GenreDto genreDto){
        genreService.save(GenreMapper.INSTANCE.toEntity(genreDto));
    }

    //todo это временный метод для генерации url, заюзать нужно один раз, чтобы url создались.
    @GetMapping("/")
    public void generateAllUrl(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam String param) throws IOException {
        if (param.equals("re")) {
            genreService.reGenerateAllUrl();
        }
        response.sendRedirect(request.getHeader("referer"));
    }
}

