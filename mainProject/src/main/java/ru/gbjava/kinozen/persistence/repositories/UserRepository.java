package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.gbjava.kinozen.persistence.entities.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findOneByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
