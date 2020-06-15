package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.persistance.entity.Collection;
import ru.gbjava.collectionservice.persistance.repository.CollectionRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public Collection findCollection(UUID id) {
        return collectionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Collection not found UUID:" + id));
    }
}
