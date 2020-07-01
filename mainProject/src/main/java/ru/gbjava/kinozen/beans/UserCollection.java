package ru.gbjava.kinozen.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Slf4j
@Setter
@Getter
@RequiredArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserCollection {

    //todo реализация в процессе

//    private final CollectionFeignClient collectionFeignClient;
//    private final ContentService contentService;
//
//    private String login;
//    private UserCollection userCollection;
//    private List<Content> contents;
//
//
//    public void init(String name) {
//        if (login == null || !login.equals(name)) {
//            login = name;
//            refreshWish();
//        }
//    }
//
//    public void refreshWish() {
//        try {
//            userCollection = collectionFeignClient.getWishList(login).getBody();
//            if (userCollection != null) {
//                contents = contentService.findWishContents(userCollection.getContents());
//            } else {
//                contents = new ArrayList<>();
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    public void addWish(String id) {
//        Content content = contentService.findById(UUID.fromString(id));
//        WishContentDto wishContentDto = WishContentDto.builder()
//                .id(content.getId())
//                .idCollection(userCollection.getId())
//                .build();
//        collectionFeignClient.addContentToWishList(wishContentDto);
//        contents.add(content);
//    }
//
//    public void deleteWish(String idContent) {
//        collectionFeignClient.deleteContentFromWishList(idContent, String.valueOf(userCollection.getId()));
//
//        for(Content con : contents) {
//            if (con.getId().equals(UUID.fromString(idContent))) {
//                contents.remove(con);
//                return;
//            }
//        }
//    }
//
//    public boolean isWished(Content content) {
//        for(Content con : contents) {
//            if (con.getId().equals(content.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }
}
