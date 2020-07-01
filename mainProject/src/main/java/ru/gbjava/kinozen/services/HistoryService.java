package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.History;
import ru.gbjava.kinozen.persistence.repositories.HistoryRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final ContentService contentService;

    public List<History> findHistoryByUserId(UUID id){

        List<History> history = historyRepository.findAllByIdUser(id).orElse(new ArrayList<>());
        history.sort((h1, h2) -> h2.getDate().compareTo(h1.getDate()));
        return history;
    }

    @Transactional
    public History save(UUID userId, UUID contentId){

       History history = historyRepository
               .findByIdUserAndContentId(userId, contentId)
               .orElse(History.builder()
                       .idUser(userId)
                       .content(contentService.findById(contentId))
                       .build());
       history.setDate(new Date());

       return historyRepository.save(history);
    }

}
