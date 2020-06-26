package ru.gbjava.kinozen.persistence.repositories;

import jdk.jfr.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {

    Optional<Content> findContentByUrl(@NonNull String url);

    List<Content> findAllByType(TypeContent type);

    @Query("SELECT c FROM Content c WHERE c.id IN :ids")
    List<Content> findByInventoryIds(@Param("ids") List<UUID> ids);
}
