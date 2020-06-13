package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.gbjava.kinozen.persistence.entities.History;

import java.util.*;

public interface HistoryRepository extends JpaRepository<History, UUID> {

    Optional<List<History>> findAllByIdUser(UUID id);

    @Query(value = "SELECT name_content FROM tbl_history h " +
            "INNER JOIN tbl_content c ON h.id_content = c.id_content " +
            "INNER JOIN tbl_user u ON h.id_user = u.id_user " +
            "WHERE u.id_user = :id ORDER BY h.date_history", nativeQuery = true)
    Set<String> findContentSetByUserId(@Param("id") UUID id);

    @Query(value = "SELECT date_history, name_content FROM tbl_history h " +
            "INNER JOIN tbl_content c ON h.id_content = c.id_content " +
            "INNER JOIN tbl_user u ON h.id_user = u.id_user " +
            "WHERE u.id_user = :id ORDER BY h.date_history", nativeQuery = true)
    Set<Object[]> findDateAndContentSetByUserId(@Param("id") UUID id);

}
