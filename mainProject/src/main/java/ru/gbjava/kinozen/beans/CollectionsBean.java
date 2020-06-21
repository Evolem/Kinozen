package ru.gbjava.kinozen.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.dto.WishCollectionDto;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CollectionsBean {

    private final CollectionFeignClient collectionFeignClient;
    private final ContentService contentService;

    private String login;
    private WishCollectionDto wishCollection;
    private List<Content> wishList;


    public void init(String name) {
        if (login == null || !login.equals(name)) {
            login = name;
            refreshWish();
        }
    }

    public void refreshWish() {
        try {
            wishCollection = collectionFeignClient.getWishCollection(login).getBody();
            if (wishCollection != null) {
                wishList = contentService.findWishContents(wishCollection.getContents());
            } else {
                wishList = new ArrayList<>();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}
