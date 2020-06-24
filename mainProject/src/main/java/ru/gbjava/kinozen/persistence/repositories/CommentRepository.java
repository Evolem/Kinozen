package ru.gbjava.kinozen.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    //Поиск по ID
    Optional<Comment> findById(@NonNull UUID id);

    //Список по пользователю
    List<Comment> findCommentsByUser(@NonNull User user);

    //Cписок по UUID сущности
    @Query("SELECT c FROM Comment c WHERE (c.idEntity=:idEpisode)")//обращаемся к базе
    List <Comment> findAllCommentByEpisodeID(UUID idEpisode);


}
