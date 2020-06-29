package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.persistence.repositories.CommentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CommentService implements CrudService<Comment, UUID> {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(UUID uuid) {
        return commentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Comment Not Found! " + uuid));
    }

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteById(UUID uuid) {
        commentRepository.deleteById(uuid);
    }

    public List<Comment> findCommentsByUser(User user) {
        return commentRepository.findCommentsByUser(user);
    }

    public List<Comment> findAllCommentByIdEntity(UUID idEntity) {
        return commentRepository.findAllByIdEntity(idEntity);
    }


}
