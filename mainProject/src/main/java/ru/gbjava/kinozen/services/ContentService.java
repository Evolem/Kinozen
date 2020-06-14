package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ContentService implements CrudService<Content, UUID> {

    private final ContentRepository contentRepository;

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @Override
    public Content findById(@NonNull UUID uuid) {
        return contentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Content not found! " + uuid));
    }

    //todo нужно получить список по типу
    public List<Content> findAllByTypeContent(TypeContent type) {
        return contentRepository.findAll();
    }

    @Override
    @Transactional
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull UUID uuid) {
        contentRepository.deleteById(uuid);
    }

    public Content findByUrl(String url) {
        return contentRepository.findContentByUrl(url).orElseThrow(() -> new RuntimeException("Content not found! " + url));
    }

    @Transactional
    public void reGenerateAllUrl() {
        List<Content> contents = contentRepository.findAll();
        for (Content c : contents) {
            c.setUrl(StringConverter.cyrillicToLatin(c.getName()));
            contentRepository.save(c);
        }
    }

    public Page<Content> findAll(Specification<Content> spec, Pageable pageable) {
        return contentRepository.findAll(spec, pageable);
    }


}
