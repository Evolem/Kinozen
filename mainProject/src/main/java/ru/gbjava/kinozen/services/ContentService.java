package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.Assert;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.ContentType;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.persistence.repositories.ContentTypeRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ContentService implements CrudService<Content, UUID> {

    private final ContentRepository contentRepository;
    private final ContentTypeRepository contentTypeRepository;

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content findById(@NonNull UUID uuid) {
        return contentRepository.findById(uuid).orElseThrow(()-> new RuntimeException("Content not found! " + uuid));
    }

    //todo нужно получить список по типу
    public List<Content> findAllByTypeContent(ContentType type){
        return contentRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Content content) {
        contentRepository.save(content);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull UUID uuid) {
        contentRepository.deleteById(uuid);
    }

    public Content findByUrl(String url){
        return contentRepository.findContentByUrl(url).orElseThrow(()-> new RuntimeException("Content not found! " + url));
    }

    @Transactional
    public void reGenerateAllUrl() {
        List<Content> contents = contentRepository.findAll();
        for (Content c : contents) {
            c.setUrl(StringConverter.cyrillicToLatin(c.getName()));
            contentRepository.save(c);
        }
    }
}
