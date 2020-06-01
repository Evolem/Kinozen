package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.TypeContent;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.persistence.repositories.TypeContentRepository;
import ru.gbjava.kinozen.services.pojo.ContentPojo;
import ru.gbjava.kinozen.services.pojo.TypeContentPojo;
import ru.gbjava.kinozen.utilites.StringConverter;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContentService{

    private final ContentRepository contentRepository;
    private final TypeContentRepository typeContentRepository;

    public ContentPojo findById(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content Not Found!"));
        return new ContentPojo(content);
    }

    public ContentPojo findByUrl(String url) {
        Content content = contentRepository.findMediaByUrl(url).orElseThrow(() -> new RuntimeException("Content Not Found!"));
        return new ContentPojo(content);
    }

    public List<TypeContentPojo> getAllTypes() {
        return typeContentRepository
                .findAll()
                .stream()
                .map(TypeContentPojo::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(ContentPojo contentPojo) {
        Content content = new Content();
//        content.setId(contentPojo.getId());
        content.setName(contentPojo.getName());
        content.setReleased(contentPojo.getReleased());
        content.setDescription(contentPojo.getDescription());
        content.setVisible(contentPojo.getVisible());
        content.setTypeContent(typeContentRepository
                .findById(contentPojo.getId())
                .orElseThrow(() -> new RuntimeException("Genre Not Found!")));
        content.setUrl(StringConverter.cyrillicToLatin(contentPojo.getName()));
        contentRepository.save(content);
    }

    //todo проверка на null
    @Transactional
    public void delete(ContentPojo contentPojo) {
        contentRepository.deleteById(contentPojo.getId());
    }

    public List<ContentPojo> getAllMedia() {
        return contentRepository
                .findAll()
                .stream()
                .map(ContentPojo::new)
                .collect(Collectors.toList());
    }

}
