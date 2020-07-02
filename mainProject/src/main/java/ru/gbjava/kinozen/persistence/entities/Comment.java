package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment {

    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    @JoinColumn(name = "id_entity")
    private UUID idEntity;

    @Column(name = "text_comment")
    private String text;

    @Column(name = "date_comment")
    private Date date;
}
