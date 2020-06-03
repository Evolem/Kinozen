package ru.gbjava.kinozen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private UUID id_comment;
    private UUID id_user;
    private String text_comment;
    private Date date_comment;
}
