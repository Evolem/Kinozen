package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.CommentDto;
import ru.gbjava.kinozen.persistence.entities.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "idEntity", target = "idEntity")
    @Mapping(source = "text", target = "textComment")
    @Mapping(source = "date", target = "dateComment")
    CommentDto toDto(Comment comment);

    @Mapping(source = "textComment", target = "text")
    @Mapping(source = "dateComment", target = "date")
    Comment toEntity(CommentDto commentDto);

    Iterable<CommentDto> toDtoList(List<Comment> comments);
}
