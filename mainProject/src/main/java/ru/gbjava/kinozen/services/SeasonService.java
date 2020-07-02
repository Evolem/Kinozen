package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.repositories.SeasonRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SeasonService implements CrudService<Season, UUID> {

    private final SeasonRepository seasonRepository;

    @Override
    public List<Season> findAll() {
        return seasonRepository.findAll();
    }

    @Override
    public Season findById(UUID uuid) {
        return seasonRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Season not found! " + uuid));
    }

    @Override
    public Season save(Season object) {
        return seasonRepository.save(object);
    }

    @Override
    public void deleteById(UUID uuid) {
        seasonRepository.deleteById(uuid);
    }

    public List<Season> findSeasonByContent(Content content) {
        return seasonRepository.findAllByContent(content);
    }

    public Season findByContentAndUrl(Content content, String url) {
        return seasonRepository.findByContentAndUrl(content, url).orElseThrow(() ->
                new RuntimeException("Season not found " + content.getUrl() + "/" + url));
    }
}
