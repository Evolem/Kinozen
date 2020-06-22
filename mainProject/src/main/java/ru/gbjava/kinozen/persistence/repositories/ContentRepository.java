package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {

    @Query(value = "SELECT * FROM tbl_content WHERE released_content >= CURRENT_DATE - INTEGER '7'"
            , nativeQuery = true)
    Set<Content> getNewContents();

    Optional<Content> findContentByUrl(@NonNull String url);

}
