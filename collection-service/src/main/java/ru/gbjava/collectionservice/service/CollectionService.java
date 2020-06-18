package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.entity.Content;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public Map<UUID, Collection> findAllCollection(@NonNull String user) {
        List<Collection> collections = collectionRepository.findAllByUser(UUID.fromString(user));

        if (collections.isEmpty()) {
            return null;
        }

        Map<UUID, Collection> userCollection = new HashMap<>();
        collections.forEach(col -> userCollection.put(col.getId(), col));
        return userCollection;
    }

    public List<UUID> getWishCollection(@NonNull String user) {
        Collection collection = collectionRepository.findByUserAndName(UUID.fromString(user), "wish").orElseThrow();
        List<UUID> result = collection.getContentList().stream().map(Content::getId).collect(Collectors.toList());
        return result;
    }
}
