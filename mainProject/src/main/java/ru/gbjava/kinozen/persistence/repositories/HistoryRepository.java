package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.History;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {

    Optional<List<History>> findAllByIdUser(UUID id);
    Optional<History> findByIdUserAndContentId(UUID userId, UUID contentId);

}
