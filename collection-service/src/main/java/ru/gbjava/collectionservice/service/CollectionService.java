package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.collectionservice.dto.WishCollectionDto;
import ru.gbjava.collectionservice.dto.WishContentDto;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.entity.Content;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;
import ru.gbjava.collectionservice.persistance.repository.ContentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final ContentRepository contentRepository;

    public Map<UUID, Collection> findAllCollection(@NonNull String user) {
        List<Collection> collections = collectionRepository.findAllByUser(user);

        if (collections.isEmpty()) {
            return null;
        }

        Map<UUID, Collection> userCollection = new HashMap<>();
        collections.forEach(col -> userCollection.put(col.getId(), col));
        return userCollection;
    }

    public WishCollectionDto getWishCollection(@NonNull String user) {
        Collection collection = collectionRepository.findByUserAndName(user, "wish").orElseThrow();
        return WishCollectionDto.builder()
                .id(collection.getId())
                .name(collection.getName())
                .contents(collection.getContentList().stream().map(Content::getId).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public void addWishContent(WishContentDto wishContent) {
        contentRepository.saveContent(wishContent.getId(), wishContent.getIdCollection());
    }

    @Transactional
    public void deleteWishContent(String idContent, String idCollection) {
        //TODO: переделать
        contentRepository.deleteContent(UUID.fromString(idContent), collectionRepository.findById(UUID.fromString(idCollection)).orElseThrow());
    }

    @Transactional
    public void createWishCollection(String login) {
        Collection collection = Collection.builder()
                .name("wish")
                .user(login)
                .build();
        collectionRepository.save(collection);
    }
}
