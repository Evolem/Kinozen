package ru.gbjava.kinozen.beans;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CollectionsBean {

    private final CollectionFeignClient collectionFeignClient;
    private final ContentService contentService;

    //TODO: найти способ получить user uuid
    private UUID user;

    private List<Content> wishList;
    private List<UUID> responseList;

    public void init(UUID user) {
        responseList = collectionFeignClient.getWishCollection(String.valueOf(user)).getBody();

        if (responseList == null) {
            wishList = new ArrayList<>();
        } else {
            wishList = contentService.findWishContents(responseList);
        }
    }


}
