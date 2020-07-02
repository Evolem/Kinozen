package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.DirectorDto;
import ru.gbjava.kinozen.dto.mappers.DirectorMapper;
import ru.gbjava.kinozen.services.DirectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/{url}")
    public String getDirectorByUrl(Model model, @PathVariable String url) {
        DirectorDto directorDto = DirectorMapper.INSTANCE.toDto(directorService.findByUrl(url));
        model.addAttribute("director", directorDto);
        return "directorPage";
    }

    @GetMapping
    public String getAll(Model model) {
        Iterable<DirectorDto> directors = DirectorMapper.INSTANCE.toDtoList(directorService.findAll());
        model.addAttribute("directors", directors);
        return "directorAll";
    }

    @GetMapping ("/addDirector")
    public String add(Model model) {
        DirectorDto directorDto = new DirectorDto();

        model.addAttribute("director", directorDto);
        return "directorEdit";
    }

    @PostMapping("/addDirector")
    public void add(DirectorDto directorDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        directorService.save(DirectorMapper.INSTANCE.toEntity(directorDto));
        response.sendRedirect(request.getHeader("referer"));
    }

    //todo это временный метод для генерации url, заюзать нужно один раз, чтобы url создались.
    @GetMapping("/")
    public void generateAllUrl(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam String param) throws IOException {
        if (param.equals("re")) {
            directorService.generateAllUrl();
        }
        response.sendRedirect(request.getHeader("referer"));
    }
}