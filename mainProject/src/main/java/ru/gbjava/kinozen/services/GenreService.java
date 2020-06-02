package ru.gbjava.kinozen.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.repositories.GenreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService implements CrudService <Genre, Long> {
    private final GenreRepository genreRepository;


    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found!"));
    }

    @Override
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void deleteBy(Long id) {
        genreRepository.deleteById(id);
    }

    public Genre findByUrl(String url){
        r
    }
}
