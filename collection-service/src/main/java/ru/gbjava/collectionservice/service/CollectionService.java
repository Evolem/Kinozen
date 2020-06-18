package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.dto.WishCollectionDto;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;

import java.util.*;

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

    public WishCollectionDto getWishCollection(@NonNull String user) {
        Collection collection = collectionRepository.findByUserAndName(UUID.fromString(user), "wish").orElseThrow();

        WishCollectionDto wishCollection = new WishCollectionDto();
        wishCollection.setId(collection.getId());
        wishCollection.setContents(new ArrayList<>());
        collection.getContentList().forEach(content -> wishCollection.getContents().add(content.getId()));

        return wishCollection;
    }
}
