package ru.gbjava.kinozen.services.facade;

import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.Content;

import java.util.List;
import java.util.UUID;

public interface CommentFacade {
    List<Comment> findAllComment();

    List<Comment> findAllCommentByEpisodeId(UUID idEpisode);

    void saveComment(Comment comment);

}
