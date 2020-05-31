package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.TypeContent;
import java.util.Optional;

public interface TypeContentRepository extends JpaRepository <TypeContent, Long> {
    Optional<TypeContent> findById(Long id);
}
