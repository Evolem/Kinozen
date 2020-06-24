package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.beans.Wish;
import ru.gbjava.kinozen.dto.WishContentDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "collectionFeignClient", url = "${collection.service.url}")
public interface CollectionFeignClient {

    @GetMapping(value = "/wish/list/{userId}")
    ResponseEntity<List<Wish>> getWishList(@PathVariable UUID userId);

    @PostMapping(value = "/wish/add")
    void addContentToWishList(@RequestBody WishContentDto contentDto);

    @DeleteMapping(value = "/wish/delete/{idContent}/{idCollection}")
    void deleteContentFromWishList(@PathVariable String idContent, @PathVariable String idCollection);

    @PostMapping(value = "/wish/create")
    void createWishList(@RequestBody String login);
}
