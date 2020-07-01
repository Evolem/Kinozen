package ru.gbjava.collectionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.service.CollectionService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collection")
public class CollectionController {

    //todo контроллер для коллекций

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


}
