package ru.gbjava.kinozen.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.repositories.GenreRepository;

@Service
@AllArgsConstructor
public class GenreService implements CrudService <Genre, Long> {
    private final GenreRepository genreRepository;


    @Override
    public Iterable<Genre> findAll() {
        return null;
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found!"));
    }

    @Override
    public void save(Genre object) {

    }

    @Override
    public void deleteBy(Long id) {

    }
}
