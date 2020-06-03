package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.ContentType;
import ru.gbjava.kinozen.services.ContentService;
import ru.gbjava.kinozen.services.ContentTypeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentFacadeImpl implements ContentFacade{

    private final ContentService contentService;
    private final ContentTypeService contentTypeService;

    @Override
    public List<Content> findAllContent() {
       return contentService.findAll();
    }

    @Override
    public Content findContentByUrl(String url) {
        return contentService.findByUrl(url);
    }

    @Override
    public void saveContent(Content content) {
        contentService.save(content);
    }

    @Override
    public void deleteContentById(UUID uuid) {
        contentService.deleteById(uuid);
    }

    @Override
    public List<ContentType> findAllTypeContent() {
        return contentTypeService.findAll();
    }
}
