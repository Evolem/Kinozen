package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.persistence.repositories.ActorRepository;
import ru.gbjava.kinozen.services.pojo.ActorPojo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Actor getActorById(int id){
        return actorRepository.findById(id).orElse(new Actor());
    }

    public List<Media> getActorMedias(int id){
        Actor actor = actorRepository.findById(id).orElse(new Actor());
        return actor.getMedias();
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
