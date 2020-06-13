package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.History;
import ru.gbjava.kinozen.persistence.repositories.HistoryRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ContentService contentService;

    public List<History> findHistoryByUserId(UUID id){
        List<History> history = historyRepository.findAllByIdUser(id).orElse(null);
        assert history != null;
        history.sort((h1, h2) -> h2.getDate().compareTo(h1.getDate()));

        return history;
    }

    @Transactional
    public History save(UUID userId, UUID contentId){
        historyRepository
                .findByIdUserAndContentId(userId, contentId)
                .ifPresent(h -> historyRepository.deleteById(h.getId()));

        History history = History.builder()
                .idUser(userId)
                .content(contentService.findById(contentId))
                .date(new Date())
                .build();

        return historyRepository.save(history);
    }


}
