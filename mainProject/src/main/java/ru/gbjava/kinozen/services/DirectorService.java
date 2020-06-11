package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.repositories.DirectorRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class DirectorService implements CrudService<Director, UUID>, UrlService<Director> {

    private final DirectorRepository directorRepository;

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director findById(UUID uuid) {
        return directorRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Director not found! " + uuid));
    }

    @Override
    @Transactional
    public Director save(Director director) {
        return directorRepository.save(director);
    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        directorRepository.deleteById(uuid);
    }

    @Override
    public Director findByUrl(String url) {
        return directorRepository.findByUrl(url).orElseThrow(() -> new RuntimeException("Director not found! " + url));
    }

    @Override
    @Transactional
    public void generateAllUrl() {
        List<Director> directors = directorRepository.findAll();
        for (Director d : directors) {
            d.setUrl(StringConverter.cyrillicToLatin(d.getFirstName() + "-" + d.getLastName()));
            directorRepository.save(d);
        }
    }
}