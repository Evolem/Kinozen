package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.ContentType;
import java.util.Optional;
import java.util.UUID;

public interface ContentTypeRepository extends JpaRepository <ContentType, UUID> {
    Optional<ContentType> findById(UUID id);
}
