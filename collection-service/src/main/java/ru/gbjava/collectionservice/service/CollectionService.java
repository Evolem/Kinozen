package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.collectionservice.dto.CollectionDto;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionService {

    //todo в процессе

    private final CollectionRepository collectionRepository;

    // зачем тут map ?
    public Map<UUID, Collection> findAllCollection(@NonNull String user) {
        List<Collection> collections = collectionRepository.findAllByUser(user);

        if (collections.isEmpty()) {
            return null;
        }

        Map<UUID, Collection> userCollection = new HashMap<>();
        collections.forEach(col -> userCollection.put(col.getId(), col));
        return userCollection;
    }

    public CollectionDto getCollectionByUserName(@NonNull String user) {
        Collection collection = collectionRepository.findByUserAndName(user, "wish").orElseThrow();
        return CollectionDto.builder()
                .id(collection.getId())
                .name(collection.getName())
                .contents(collectionRepository.testAllContentByCollection(collection.getId()))
                .build();
    }

    @Transactional
    public void addContentToCollection(UUID idContent, UUID idCollection) {
        collectionRepository.saveContent(idContent, idCollection);
    }

    //todo переделать
    @Transactional
    public void deleteContentFromCollection(String idContent, String idCollection) {
        collectionRepository.deleteContent(UUID.fromString(idContent), collectionRepository.findById(UUID.fromString(idCollection)).orElseThrow());
    }

    //todo переделать входные данные
    @Transactional
    public Collection createCollection(String login) {
        Collection collection = Collection.builder()
                .name("wish")
                .user(login)
                .build();
        return collectionRepository.save(collection);
    }
}
