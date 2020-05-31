package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.TypeContent;
import java.util.Optional;

public interface TypeMediaRepository extends JpaRepository <TypeContent, Integer> {
    Optional<TypeContent> findById(Long id);
}
