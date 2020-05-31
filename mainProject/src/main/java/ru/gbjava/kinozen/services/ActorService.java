package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.repositories.ActorRepository;
import ru.gbjava.kinozen.services.pojo.ActorPojo;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Actor getActorById(int id){
        return actorRepository.findById(id).orElse(new Actor());
    }

    @Transactional
    public void save(ActorPojo actorPojo){
        Actor actor = Actor.builder()
                .firstName(actorPojo.getFirstName())
                .lastName(actorPojo.getLastName())
                .description(actorPojo.getDescription())
                .build();
        actorRepository.save(actor);
    }

}
