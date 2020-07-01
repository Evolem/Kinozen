package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID>, JpaSpecificationExecutor<Content> {

    @Query(value = "SELECT * FROM tbl_content WHERE released_content >= CURRENT_DATE - INTEGER '7'"
            , nativeQuery = true)
    Set<Content> getNewContents();

    Optional<Content> findContentByUrl(@NonNull String url);


    List<Content> findAllByType(TypeContent type);

    List<Content> findAllByTypeAndGenres(TypeContent type, Genre genre);

    @Query("SELECT c FROM Content c WHERE c.id IN :ids")
    List<Content> findByInventoryIds(@Param("ids") List<UUID> ids);
}
