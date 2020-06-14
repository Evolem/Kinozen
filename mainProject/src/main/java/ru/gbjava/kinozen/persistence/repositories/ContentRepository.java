package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID>, JpaSpecificationExecutor<Content> {

    Optional<Content> findContentByUrl(@NonNull String url);
}
