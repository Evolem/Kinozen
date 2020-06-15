package ru.gbjava.collectionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.service.CollectionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping(value = "/{id}")
    public Collection getCollection(@PathVariable String id) {
        return collectionService.findCollection(UUID.fromString(id));
    }

}
