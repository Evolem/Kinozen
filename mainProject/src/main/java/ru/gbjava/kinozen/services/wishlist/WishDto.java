package ru.gbjava.kinozen.services.wishlist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.dto.ContentDto;

@Getter
@Setter
@Builder
public class WishDto {
    private String id;
    private String userId;
    private String contentId;
    private String added;

    @JsonIgnore
    private ContentDto content;
}
