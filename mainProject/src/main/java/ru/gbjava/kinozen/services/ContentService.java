package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.TypeContent;
import ru.gbjava.kinozen.persistence.repositories.MediaRepository;
import ru.gbjava.kinozen.persistence.repositories.TypeMediaRepository;
import ru.gbjava.kinozen.services.pojo.ContentPojo;
import ru.gbjava.kinozen.utilites.StringConverter;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContentService {

    private final MediaRepository mediaRepository;
    private final TypeMediaRepository typeMediaRepository;

    public ContentPojo findById(Integer id) {
        Content content = mediaRepository.findById(id).orElse(new Content()); //todo бросить тут исключение
        return new ContentPojo(content);
    }

    public ContentPojo findByUrl(String url) {
        Content content = mediaRepository.findMediaByUrl(url).orElse(new Content()); //todo бросить тут исключение
        return new ContentPojo(content);
    }

    //можно сделать билдер
    @Transactional
    public void save(ContentPojo contentPojo) {
        Content content = new Content();
        content.setId(contentPojo.getId());
        content.setName(contentPojo.getName());
        content.setReleased(contentPojo.getReleased());
        content.setDescription(contentPojo.getDescription());
        content.setVisible(contentPojo.getVisible());
        content.setTypeContent(typeMediaRepository.findById(contentPojo.getId()).
                orElse(new TypeContent())); //todo бросить тут исключение
        content.setUrl(StringConverter.cyrillicToLatin(contentPojo.getName()));
        mediaRepository.save(content);
    }

    //todo проверка на null
    @Transactional
    public void delete(ContentPojo contentPojo) {
        mediaRepository.deleteById(contentPojo.getId().intValue());
    }

    public List<ContentPojo> getAllMedia() {
        return mediaRepository
                .findAll()
                .stream()
                .map(ContentPojo::new)
                .collect(Collectors.toList());
    }

}
