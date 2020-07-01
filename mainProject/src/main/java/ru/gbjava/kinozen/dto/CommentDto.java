package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.User;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private UUID id;
    private User user;
    private UUID idEntity;
    private String textComment;
    private Date dateComment;


}
