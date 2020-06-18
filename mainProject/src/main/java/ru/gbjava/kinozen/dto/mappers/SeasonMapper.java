package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.SeasonDto;
import ru.gbjava.kinozen.persistence.entities.Season;

import java.util.List;

@Mapper
public interface SeasonMapper {

    SeasonMapper INSTANCE = Mappers.getMapper(SeasonMapper.class);

    @Mapping(source = "season.content", target = "content")
    SeasonDto toDto(Season season);

    @Mapping(target = "content", source = "seasonDto.content")
    @Mapping(target = "url", source = "seasonDto.numberSeason", qualifiedByName = "generateUrl")
    Season toEntity(SeasonDto seasonDto);

    Iterable<SeasonDto> toDtoList(List<Season> seasons);

//    @Named("toContentTest")
//    public static Content toContentTest (ContentDto contentDto){
//        return ContentMapper.INSTANCE.toEntity(contentDto);
//    }

    @Named("generateUrl")
    static String generateUrl(Integer number) {
        return "season-" + number;
    }
}
