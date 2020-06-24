package ru.gbjava.kinozen.beans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WishList {

    private final UserService userService;
    private final ContentService contentService;
    private final CollectionFeignClient collectionFeignClient;

    private UUID userId;
    private List<WishProxy> wishList;

    @PostConstruct
    private void init() {
        // Инициализация user id для получения wishlist
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            this.userId = userService.findByLogin(currentUserName).getId();
            log.info("Инициализирован user id для wishlist: " + userId);
        }
        refresh();
    }

    public void refresh() {
        try {
            List<Wish> wishes = collectionFeignClient.getWishList(userId).getBody();

            //todo пока не понятно, как лучше обработать данные, чтобы получить Content внутри wish,
            // после десериализации, для этого пока сделали Proxy
            for (Wish wish : Objects.requireNonNull(wishes)) {
                WishProxy wishProxy = new WishProxy();
                wishProxy.setId(wish.getId());
                wishProxy.setContent(contentService.findById(UUID.fromString(wish.getId())));
                wishProxy.setAdded(wish.getAdded());
            }


        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<WishProxy> getWishList() {
        return wishList;
    }
}
