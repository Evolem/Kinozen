package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Episode;
import ru.gbjava.kinozen.persistence.entities.Season;
import ru.gbjava.kinozen.persistence.repositories.EpisodeRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EpisodeService implements CrudService<Episode, UUID> {

    private final EpisodeRepository episodeRepository;

    @Override
    public List<Episode> findAll() {
        return episodeRepository.findAll();
    }

    @Override
    public Episode findById(UUID uuid) {
        return episodeRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Episode not found! " + uuid));
    }

    @Override
    public Episode save(Episode object) {
        object.setAdded(new Date());
        return episodeRepository.save(object);
    }

    @Override
    public void deleteById(UUID uuid) {
        episodeRepository.deleteById(uuid);
    }

    public List<Episode> findAllBySeason(Season season){
        return episodeRepository.findAllBySeason(season);
    }
}
