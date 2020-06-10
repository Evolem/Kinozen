package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findOneByLogin(@NonNull String login);

    boolean existsByLogin(@NonNull String login);

    boolean existsByEmail(@NonNull String email);
}
