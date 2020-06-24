package ru.gbjava.kinozen.beans;


import lombok.Getter;
import lombok.Setter;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;

@Getter
@Setter
public class WishProxy {

    // Это возможно временный класс, пока не найдем оптимальное решение

    private String id;
    private Content content;
    private String added;

    public String getContentId(){
        return content.getId().toString();
    }

    public ContentDto getContentDto(){
        return ContentMapper.INSTANCE.toDto(content);
    }

}
