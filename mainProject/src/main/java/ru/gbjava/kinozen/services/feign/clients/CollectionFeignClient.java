package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gbjava.kinozen.dto.WishCollectionDto;

@FeignClient(name = "collectionFeignClient", url = "${collection.service.url}")
public interface CollectionFeignClient {

    @GetMapping(value = "/wish/{user}")
    ResponseEntity<WishCollectionDto> getWishCollection(@PathVariable String user);
}
