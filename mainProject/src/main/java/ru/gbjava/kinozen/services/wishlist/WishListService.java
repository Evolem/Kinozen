package ru.gbjava.kinozen.services.wishlist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.gbjava.kinozen.dto.WishDto;
import ru.gbjava.kinozen.dto.mappers.ContentMapper;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.feign.clients.CollectionFeignClient;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        wishList = new ArrayList<>();
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
            wishList.clear();
            wishList = collectionFeignClient.getWishList(userId).getBody();
            //todo не забыть обработать тут исключение, иначе лист не прогрузится
            for (WishDto w : Objects.requireNonNull(wishList)) {
                w.setContent(ContentMapper.INSTANCE.toDto(contentService.findById(UUID.fromString(w.getContentId()))));
            }
        } catch (Exception e) {
            log.error("Ошибка при получении wishlist: " + e.getMessage());
        }
    }

    public void addWish(UUID idContent) {
        OffsetDateTime dateTime = OffsetDateTime.now();
        WishDto wishDto = WishDto.builder()
                .userId(userId.toString())
                .contentId(idContent.toString())
                .added(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .build();
        collectionFeignClient.addContentToWishList(wishDto);
        refresh();
    }


    public List<WishDto> getWishList() {
        return wishList;
    }

    public boolean isWished(Content content) {
        for (WishDto w : wishList) {
            if (w.getContent().getId().equals(content.getId())) {
                return true;
            }
        }
        return false;
    }

    public void deleteWish(UUID idContent) {
        for (WishDto w : wishList) {
            if (w.getContent().getId().toString().equals(idContent.toString())) {
                collectionFeignClient.deleteContentFromWishList(UUID.fromString(w.getId()));
                wishList.remove(w);
                return;
            }
        }
    }
}
