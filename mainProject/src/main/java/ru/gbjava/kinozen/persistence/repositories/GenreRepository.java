package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
