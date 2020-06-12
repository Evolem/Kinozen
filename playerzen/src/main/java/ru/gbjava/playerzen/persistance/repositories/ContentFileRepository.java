package ru.gbjava.playerzen.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.gbjava.playerzen.persistance.entities.ContentFile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    Optional<ContentFile> findByContent(UUID content);
}
