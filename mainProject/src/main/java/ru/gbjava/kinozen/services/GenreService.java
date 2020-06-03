package ru.gbjava.kinozen.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.repositories.GenreRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GenreService implements CrudService<Genre, UUID> {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(UUID id) {
        return genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found! " + id));
    }

    @Override
    @Transactional
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        genreRepository.deleteById(id);
    }

    public Genre findByUrl(String url) {
        return genreRepository.findByUrl(url).orElseThrow(() -> new RuntimeException("Genre not found! " + url));
    }

    @Transactional
    public void reGenerateAllUrl() {
        List<Genre> genres = genreRepository.findAll();
        for (Genre g : genres) {
            g.setUrl(StringConverter.cyrillicToLatin(g.getName()));
            genreRepository.save(g);
        }
    }
}
