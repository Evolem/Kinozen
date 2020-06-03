package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ru.gbjava.kinozen.persistence.entities.Comment;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.User;
import ru.gbjava.kinozen.persistence.repositories.CommentRepository;
import org.springframework.data.jpa.domain.Specification;

import ru.gbjava.kinozen.services.pojo.CommentPojo;
import ru.gbjava.kinozen.services.pojo.ContentPojo;
import ru.gbjava.kinozen.services.pojo.UserPojo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;


    public CommentPojo findById(Long id) {
        Comment comment = commentRepository.findById(id).orElse(new Comment()); //todo бросить тут исключение
        return new CommentPojo(comment);

    }

    public List<CommentPojo> CommentedListUserID(Long userid) {
        return commentRepository.CommentedListByUserID(userid);
    }


    @Transactional
    public void save(CommentPojo commentPojo) {

        Comment comment = new Comment();
        comment.setText_comment(commentPojo.getText_comment());
        comment.setDate_comment(commentPojo.getDate_comment());

      //  content.setReleased(contentPojo.getReleased());
     //   content.setDescription(contentPojo.getDescription());
    ///    content.setVisible(contentPojo.getVisible());
     //   Comment comment = Comment.builder()
     //           .id_user(commentPojo.getId_user())
     //           .uuid_content(111111)
     //           .text_comment(commentPojo.getText_comment())
    //            .build();
        commentRepository.save(comment);
    }


    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

}
