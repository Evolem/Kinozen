package ru.gbjava.kinozen.services.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gbjava.kinozen.dto.WishCollectionDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@FeignClient(name = "collectionFeignClient", url = "${collection.service.url}")
public interface CollectionFeignClient {

//    @GetMapping(value = "/{user}")
//    ResponseEntity<Map<UUID, WishCollectionDto>> getUserCollections(@PathVariable String user);

    //TODO: ResponseEntity приходит с id, но без List<>
//    @GetMapping(value = "/wish/{user}", consumes = "application/json")
//    WishCollectionDto getWishCollection(@PathVariable String user);

    @GetMapping(value = "/wish/{user}")
    ResponseEntity<List<UUID>> getWishCollection(@PathVariable String user);
}
