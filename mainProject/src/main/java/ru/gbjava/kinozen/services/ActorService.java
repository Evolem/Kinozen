package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.repositories.ActorRepository;
import ru.gbjava.kinozen.dto.ActorDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActorService implements CrudService<Actor, UUID> {

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(UUID uuid) {
        return actorRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Actor not found! " + uuid));
    }

    @Override
    @Transactional
    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        actorRepository.deleteById(uuid);
    }
}
