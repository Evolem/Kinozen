package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.ContentType;
import ru.gbjava.kinozen.persistence.repositories.ContentTypeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentTypeService implements CrudService<ContentType, UUID> {
    private final ContentTypeRepository contentTypeRepository;

    @Override
    public List<ContentType> findAll() {
        return contentTypeRepository.findAll();
    }

    @Override
    public ContentType findById(UUID uuid) {
        return contentTypeRepository.findById(uuid).orElseThrow(()-> new RuntimeException("Type not found! " + uuid));
    }

    @Override
    public void save(ContentType object) {
        contentTypeRepository.save(object);
    }

    @Override
    public void deleteById(UUID uuid) {
        contentTypeRepository.deleteById(uuid);
    }
}
