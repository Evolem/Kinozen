package ru.gbjava.collectionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.collectionservice.dto.WishCollectionDto;
import ru.gbjava.collectionservice.dto.WishContentDto;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.service.CollectionService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping(value = "/{user}")
    public ResponseEntity<Map<UUID, Collection>> getUserCollection(@PathVariable String user) {
        Map<UUID, Collection> result = collectionService.findAllCollection(user);

        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping(value = "/wish/{user}")
    public ResponseEntity<WishCollectionDto> getWishCollection(@PathVariable String user) {
        WishCollectionDto result = collectionService.getWishCollection(user);

        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(value = "wish/add")
    public void addWishContent(@RequestBody WishContentDto wishContent) {
        collectionService.addWishContent(wishContent);
    }

    @DeleteMapping(value = "wish/delete/{idContent}/{idCollection}")
    public void deleteWishContent(@PathVariable String idContent, @PathVariable String idCollection) {
        collectionService.deleteWishContent(idContent, idCollection);
    }

    @PostMapping(value = "/wish/create")
    public void createWishCollection(@RequestBody String login) {
        collectionService.createWishCollection(login);
    }

}
