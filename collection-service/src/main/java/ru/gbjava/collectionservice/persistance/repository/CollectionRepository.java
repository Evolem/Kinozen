package ru.gbjava.collectionservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.collectionservice.persistance.entity.Collection;

import java.util.UUID;

public interface CollectionRepository extends JpaRepository<Collection, UUID> {
}
