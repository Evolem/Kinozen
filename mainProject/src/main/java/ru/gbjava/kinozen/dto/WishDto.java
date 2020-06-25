package ru.gbjava.kinozen.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WishDto {
    private String id;
    private String userId;
    private ContentDto content;
    private String added;
}
