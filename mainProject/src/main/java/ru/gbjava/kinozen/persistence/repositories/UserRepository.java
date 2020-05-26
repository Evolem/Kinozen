package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.gbjava.kinozen.persistence.entities.User;

/**
 * Created by IntelliJ Idea.
 * User: Якимов В.Н.
 * E-mail: yakimovvn@bk.ru
 */

public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByLogin(String login);
    boolean existsByLogin(String login);
}
