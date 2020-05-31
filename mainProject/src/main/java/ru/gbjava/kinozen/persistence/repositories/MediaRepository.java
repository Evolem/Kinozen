package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Content, Integer> {
    Optional<Content> findMediaByUrl(String url);
}
