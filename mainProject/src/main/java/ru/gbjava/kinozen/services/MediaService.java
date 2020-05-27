package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.persistence.repositories.MediaRepository;
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

    public MediaPojo findById (Integer id)  {
        MediaPojo mediaPojo = new MediaPojo();
        Media media = mediaRepository.findById(id).orElse(new Media());

        mediaPojo.setId(media.getId());
        mediaPojo.setName(media.getName());
        mediaPojo.setReleased(media.getReleased());
        mediaPojo.setDescription(media.getDescription());
        mediaPojo.setVisible(media.getVisible());
        mediaPojo.setUrl(media.getUrl());
        return mediaPojo;
    }

    public MediaPojo findByAll (Integer id)  {
        MediaPojo mediaPojo = new MediaPojo();
        Media media = mediaRepository.findById(id).orElse(new Media());

        mediaPojo.setId(media.getId());
        mediaPojo.setName(media.getName());
        mediaPojo.setReleased(media.getReleased());
        mediaPojo.setDescription(media.getDescription());
        mediaPojo.setVisible(media.getVisible());
        mediaPojo.setUrl(media.getUrl());
        return mediaPojo;
    }

    public MediaPojo findByUrl (String url) {
        MediaPojo mediaPojo = new MediaPojo();
        Media media = mediaRepository.findByUrl(url).orElse(new Media());

        mediaPojo.setId(media.getId());
        mediaPojo.setName(media.getName());
        mediaPojo.setReleased(media.getReleased());
        mediaPojo.setDescription(media.getDescription());
        mediaPojo.setVisible(media.getVisible());
        mediaPojo.setUrl(media.getUrl());
        return mediaPojo;
    }

    @Transactional
    public void addMedia (MediaPojo mediaPojo) {
        Media media = new Media();

        media.setName(mediaPojo.getName());
        media.setReleased(mediaPojo.getReleased());
        media.setDescription(mediaPojo.getDescription());
        media.setVisible(mediaPojo.getVisible());
        media.setUrl(StringConverter.cyrillicToLatin(mediaPojo.getName()));
        mediaRepository.save(media);
    }

    @Transactional
    public void delete (MediaPojo mediaPojo) {
        if (mediaPojo.getId() == null) {
            throw new EntityNotFoundException("Ошибка при удалении!");
        }
        mediaRepository.deleteById(mediaPojo.getId());
    }

    //TODO
    @Transactional
    public void update (MediaPojo mediaPojo) {
        Media media = new Media();

        media.setName(mediaPojo.getName());
        media.setReleased(mediaPojo.getReleased());
        media.setDescription(mediaPojo.getDescription());
        media.setVisible(mediaPojo.getVisible());
        media.setUrl(StringConverter.cyrillicToLatin(mediaPojo.getName()));
    }


    public List<MediaPojo> allMedia () {
        List<MediaPojo> mediaList = mediaRepository
                .findAllOptionalMediaList()
                .orElse(new ArrayList<>())
                .stream()
                .map(MediaPojo::new)
                .collect(Collectors.toList());

        return mediaList;
    }

}
