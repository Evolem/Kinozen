package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.dto.TypeContentDto;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.TypeContent;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.persistence.repositories.TypeContentRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContentService{

    private final ContentRepository contentRepository;
    private final TypeContentRepository typeContentRepository;

    public ContentDto findById(Long id) {
        Content content = contentRepository.findById(id).orElseThrow(() -> new RuntimeException("Content Not Found!"));
        return new ContentDto(content);
    }

    public ContentDto findByUrl(String url) {
        Content content = contentRepository.findMediaByUrl(url).orElseThrow(() -> new RuntimeException("Content Not Found!"));
        return new ContentDto(content);
    }

    public List<TypeContentDto> getAllTypes() {
        return typeContentRepository
                .findAll()
                .stream()
                .map(TypeContentDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(ContentDto contentDto) {
        Content content = new Content();
//        content.setId(contentPojo.getId());
        content.setName(contentDto.getName());
        content.setReleased(contentDto.getReleased());
        content.setDescription(contentDto.getDescription());
        content.setVisible(contentDto.getVisible());
        content.setTypeContent(typeContentRepository
                .findById(contentDto.getId())
                .orElseThrow(() -> new RuntimeException("Genre Not Found!")));
        content.setUrl(StringConverter.cyrillicToLatin(contentDto.getName()));
        contentRepository.save(content);
    }

    //todo проверка на null
    @Transactional
    public void delete(ContentDto contentDto) {
        contentRepository.deleteById(contentDto.getId());
    }

    public List<ContentDto> getAllContent() {
        return contentRepository
                .findAll()
                .stream()
                .map(ContentDto::new)
                .collect(Collectors.toList());
    }

}
