package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.gbjava.kinozen.persistence.entities.Role;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role getByRole(String role);
}
