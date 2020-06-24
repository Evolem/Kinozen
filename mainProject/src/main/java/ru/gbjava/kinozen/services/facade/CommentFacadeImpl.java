package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.CommentService;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentFacadeImpl implements CommentFacade {
    private final CommentService commentService;

    @Override
    public List<Comment> findAllComment() {
        return commentService.findAll();
    }

    public List<Comment> findAllCommentByEpisodeId(UUID idEpisode) {
        return commentService.findAllCommentByEpisodeID(idEpisode);
    }

    @Override
    public void saveComment(Comment comment) {

        if (comment.getText().trim().length() != 0) //проверка на пустоту строки
            commentService.save(comment);
    }

}
