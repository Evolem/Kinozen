package ru.gbjava.collectionservice.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.collectionservice.persistance.entity.Wish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishListRepository extends JpaRepository<Wish, UUID> {

    Optional<List<Wish>> findAllByUserId(UUID userId);
}
