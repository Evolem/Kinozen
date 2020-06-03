package ru.gbjava.kinozen.services.facade;

import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.ContentType;

import java.util.List;
import java.util.UUID;

public interface ContentFacade {
    List<Content> findAllContent();
    Content findContentByUrl(String url);
    void saveContent (Content content);
    void deleteContentById(UUID uuid);

    List<ContentType> findAllTypeContent();
}
