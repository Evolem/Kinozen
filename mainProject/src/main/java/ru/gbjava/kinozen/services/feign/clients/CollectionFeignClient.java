package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.kinozen.dto.WishCollectionDto;
import ru.gbjava.kinozen.dto.WishContentDto;

@FeignClient(name = "collectionFeignClient", url = "${collection.service.url}")
public interface CollectionFeignClient {

    @GetMapping(value = "/wish/{user}")
    ResponseEntity<WishCollectionDto> getWishCollection(@PathVariable String user);

    @PostMapping(value = "/wish/add")
    void addWishContent(@RequestBody WishContentDto contentDto);

    @DeleteMapping(value = "/wish/delete/{idContent}/{idCollection}")
    void deleteWishContent(@PathVariable String idContent, @PathVariable String idCollection);
}
