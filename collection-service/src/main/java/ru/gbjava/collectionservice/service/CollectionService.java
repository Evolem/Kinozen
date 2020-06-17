package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public Collection findCollection(@NonNull String id) {
        return collectionRepository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException("Collection not found UUID:" + id));
    }

    public Map<UUID, Collection> findAllCollection(@NonNull String user) {
        List<Collection> collections = collectionRepository.findAllByUser(UUID.fromString(user));
        Map<UUID, Collection> userCollection = new HashMap<>();
        collections.forEach(col -> userCollection.put(col.getId(), col));
        return userCollection;
    }
}
