package ru.gbjava.collectionservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gbjava.collectionservice.persistance.entity.Content;

import java.lang.annotation.Native;
import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {

    @Modifying
    @Query(value = "insert into tbl_content_collection (id_content, id_collection) values (:content, :collection)", nativeQuery = true)
    void saveContent(@Param("content") UUID content, @Param("collection") UUID collection);
}
