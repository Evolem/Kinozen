package ru.gbjava.kinozen.services.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gbjava.kinozen.persistence.entities.Comment;

//Pozdeyev D.M. Date:02.06.2020

import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPojo {

    private Long id_comment;
    private Long id_user;
    private String text_comment;
    private Date date_comment;



    public CommentPojo(Comment comment) {
       this.id_comment = comment.getId();
        this.id_user= comment.getUser().getId();
        this.text_comment = comment.getText_comment();
        this.date_comment = comment.getDate_comment();
    }


}
