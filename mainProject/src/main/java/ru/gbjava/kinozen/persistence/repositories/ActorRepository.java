package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
