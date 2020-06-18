package ru.gbjava.collectionservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbjava.collectionservice.persistance.entity.Collection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, UUID> {

    List<Collection> findAllByUser(UUID user);

    Optional<Collection> findByUserAndName(String user, String name);
}
