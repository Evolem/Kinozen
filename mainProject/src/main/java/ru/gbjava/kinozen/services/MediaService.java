package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.persistence.entities.TypeMedia;
import ru.gbjava.kinozen.persistence.repositories.MediaRepository;
import ru.gbjava.kinozen.persistence.repositories.TypeMediaRepository;
import ru.gbjava.kinozen.services.pojo.MediaPojo;
import ru.gbjava.kinozen.utilites.StringConverter;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;
    private final TypeMediaRepository typeMediaRepository;

    public MediaPojo findById(Integer id) {
        Media media = mediaRepository.findById(id).orElse(new Media()); //todo бросить тут исключение
        return new MediaPojo(media);
    }

    public MediaPojo findByUrl(String url) {
        Media media = mediaRepository.findMediaByUrl(url).orElse(new Media()); //todo бросить тут исключение
        return new MediaPojo(media);
    }

    //можно сделать билдер
    @Transactional
    public void save(MediaPojo mediaPojo) {
        Media media = new Media();
        media.setId(mediaPojo.getId());
        media.setName(mediaPojo.getName());
        media.setReleased(mediaPojo.getReleased());
        media.setDescription(mediaPojo.getDescription());
        media.setVisible(mediaPojo.getVisible());
        media.setTypemedia(typeMediaRepository.findById(mediaPojo.getId()).
                orElse(new TypeMedia())); //todo бросить тут исключение
        media.setUrl(StringConverter.cyrillicToLatin(mediaPojo.getName()));
        mediaRepository.save(media);
    }

    //todo проверка на null
    @Transactional
    public void delete(MediaPojo mediaPojo) {
        mediaRepository.deleteById(mediaPojo.getId());
    }

    public List<MediaPojo> getAllMedia() {
        return mediaRepository
                .findAll()
                .stream()
                .map(MediaPojo::new)
                .collect(Collectors.toList());
    }

}
