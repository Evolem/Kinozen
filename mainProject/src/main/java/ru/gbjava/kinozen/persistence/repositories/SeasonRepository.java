package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;

import java.util.List;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID> {
   List<Season> findAllByContent(Content content);
}
