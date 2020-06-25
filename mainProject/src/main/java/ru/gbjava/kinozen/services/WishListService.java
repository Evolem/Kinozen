package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.beans.Wish;
import ru.gbjava.kinozen.beans.WishDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WishListService {

    private final UserService userService;
    private final ContentService contentService;
    private final CollectionFeignClient collectionFeignClient;

    private UUID userId;
    private List<WishDto> wishList;

    @PostConstruct
    private void init() {
        this.wishList = new ArrayList<>();
        // Инициализация user id для получения wishlist
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            userId = userService.findByLogin(currentUserName).getId();
            log.info("Инициализирован user id для wishlist: " + userId);
        }
        refresh();
    }

    public void refresh() {
        try {
            List<Wish> tmpWishList = collectionFeignClient.getWishList(userId).getBody();
            wishList.clear();
            //todo получение Content внутри wish, пока так
            if (tmpWishList != null) {
                for (Wish wish : tmpWishList) {
                    WishDto wishDto = new WishDto();
                    wishDto.setId(wish.getId());
                    wishDto.setContent(ContentMapper.INSTANCE.toDto(contentService.findById(UUID.fromString(wish.getContentId()))));
                    wishDto.setAdded(wish.getAdded());
                    wishList.add(wishDto);
                }
            }

        } catch (Exception e) {
            log.error("Ошибка при получении wishlist: " + e.getMessage());
        }
    }

    public List<WishDto> getWishList() {
        return wishList;
    }


}
