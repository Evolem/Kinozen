package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gbjava.kinozen.persistence.entities.Director;


@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Director findOneByFirstnameDirectorAndLastnameDirector(String firstname, String lastname);
    boolean existsByFirstnameDirectorAndLastnameDirector(String firstname, String lastname);
}