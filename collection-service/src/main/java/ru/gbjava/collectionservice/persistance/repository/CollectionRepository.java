package ru.gbjava.collectionservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gbjava.collectionservice.persistance.entity.Collection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, UUID> {

    List<Collection> findAllByUser(String user);

    Optional<Collection> findByUserAndName(String user, String name);

    @Query(value = "SELECT Cast(c.id_content as varchar) FROM tbl_content_collection c WHERE id_collection = :col", nativeQuery = true)
    List<UUID> testAllContentByCollection(@Param("col") UUID id);

    @Query(value = "SELECT c.id_content FROM tbl_content_collection c INNER JOIN tbl_collection cc on c.id_collection = cc.id_collection WHERE cc.name_user = :name_user", nativeQuery = true)
    List<UUID> findAllContentByUserName(@Param("name_user") String name);

    @Modifying
    @Query(value = "insert into tbl_content_collection (id_content, id_collection) values (:content, :collection)", nativeQuery = true)
    void saveContent(@Param("content") UUID content, @Param("collection") UUID collection);

    @Modifying
    @Query(value = "DELETE FROM tbl_content_collection cc where cc.id_content = :content and cc.id_collection = :collection", nativeQuery = true)
    void deleteContent(@Param("content") UUID content, @Param("collection") Collection collection);
}
