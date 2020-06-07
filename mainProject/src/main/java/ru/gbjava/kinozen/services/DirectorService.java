package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.repositories.DirectorRepository;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class DirectorService implements CrudService<Director, UUID> {

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


}