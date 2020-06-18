package ru.gbjava.kinozen.beans;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import javax.annotation.PostConstruct;
import java.security.Principal;
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

    //TODO: найти способ получить user uuid

    private String login;

    private List<Content> wishList;
    private List<UUID> responseList;


//    @PostConstruct
    public void init(String name) {
        if (login == null || !login.equals(name)) {
            login = name;
            refreshWish();
        }
    }

    public void refreshWish() {
        try {
            responseList = collectionFeignClient.getWishCollection(login).getBody();
            if (responseList == null) {
                wishList = new ArrayList<>();
            } else {
                wishList = contentService.findWishContents(responseList);
            }
        } catch (Exception e) {
            log.error(e.getMessage());

        }
    }


}
