package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.WishDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "collectionFeignClient", url = "${collection.service.url}")
public interface CollectionFeignClient {

    /**
     * Wishlist
     */

    @GetMapping(value = "/wish/list/{userId}")
    ResponseEntity<List<WishDto>> getWishList(@PathVariable UUID userId);

    @PostMapping(value = "/wish/add")
    ResponseEntity<WishDto> addContentToWishList(@RequestBody WishDto wishDto);

    @DeleteMapping(value = "/wish/delete/{idWish}")
    void deleteContentFromWishList(@PathVariable UUID idWish);

    /**
     * UserCollections
     */

}
