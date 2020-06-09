package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.Director;

import java.util.Optional;
import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
    Optional<Director> findByUrl(String url);
}