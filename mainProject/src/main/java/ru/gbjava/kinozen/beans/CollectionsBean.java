package ru.gbjava.kinozen.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.dto.WishCollectionDto;
import ru.gbjava.kinozen.dto.WishContentDto;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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

    public void addWish(String id) {
        Content content = contentService.findById(UUID.fromString(id));
        WishContentDto wishContentDto = WishContentDto.builder()
                .id(content.getId())
                .idCollection(wishCollection.getId())
                .build();
        collectionFeignClient.addWishContent(wishContentDto);
        wishList.add(content);
    }

    public void deleteWish(String idContent) {
        collectionFeignClient.deleteWishContent(idContent, String.valueOf(wishCollection.getId()));

        for(Content con : wishList) {
            if (con.getId().equals(UUID.fromString(idContent))) {
                wishList.remove(con);
                return;
            }
        }
    }

    public boolean isWished(Content content) {
        for(Content con : wishList) {
            if (con.getId().equals(content.getId())) {
                return true;
            }
        }
        return false;
    }
}
