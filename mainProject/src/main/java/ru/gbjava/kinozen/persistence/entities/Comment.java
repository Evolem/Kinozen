package ru.gbjava.kinozen.persistence.entities;


//Pozdeyev D.M. Date:01.06.2020

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tbl_comment")
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "uuid_content")
 //   private Content content;

    @Column(name = "text_comment")
    private String text_comment;

    @Column(name = "date_comment")
    private Date date_comment;

    public Comment(User user, String text_comment, Date date_comment) {
        this.user = user;
 //      this.content= content;
        this.text_comment = text_comment;
        this.date_comment = date_comment;
    }


}
