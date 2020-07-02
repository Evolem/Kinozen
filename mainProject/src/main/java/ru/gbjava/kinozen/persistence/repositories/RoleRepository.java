package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> getByRole(@NonNull String role);
}
