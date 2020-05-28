package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbjava.kinozen.persistence.entities.TypeMedia;
import java.util.Optional;

public interface TypeMediaRepository extends JpaRepository <TypeMedia, Integer> {
    Optional<TypeMedia> findById(Integer id);
}
