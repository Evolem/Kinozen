package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {

    Optional<Content> findContentByUrl(@NonNull String url);

    @Query("SELECT c FROM Content c WHERE c.id IN :ids" )
    List<Content> findByInventoryIds(@Param("ids") List<UUID> ids);
}
