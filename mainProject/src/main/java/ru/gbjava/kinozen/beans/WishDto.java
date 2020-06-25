package ru.gbjava.kinozen.beans;


import lombok.Getter;
import lombok.Setter;
import ru.gbjava.kinozen.dto.ContentDto;

@Getter
@Setter
public class WishDto {

    // todo

    private String id;
    private ContentDto content;
    private String added;

    public String getContentId(){
        return content.getId().toString();
    }

}
