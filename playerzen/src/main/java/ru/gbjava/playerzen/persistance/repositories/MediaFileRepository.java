package ru.gbjava.playerzen.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.gbjava.playerzen.persistance.entities.MediaFile;

@Repository
public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
    //TODO: обдумать необходимость репозитория
    MediaFile findByName(String name);
}
