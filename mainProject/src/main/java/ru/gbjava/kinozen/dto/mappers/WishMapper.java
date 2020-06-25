package ru.gbjava.kinozen.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.gbjava.kinozen.dto.WishDto;
import ru.gbjava.kinozen.services.wishlist.Wish;

import java.util.UUID;

@Mapper
public interface WishMapper {
    WishMapper INSTANCE = Mappers.getMapper(WishMapper.class);

    @Mapping(target = "contentId", source = "wishDto.content.id", qualifiedByName ="idToString")
    Wish toJson (WishDto wishDto);

    @Named("idToString")
    static String idToString(UUID uuid){
        return uuid.toString();
    }
}
