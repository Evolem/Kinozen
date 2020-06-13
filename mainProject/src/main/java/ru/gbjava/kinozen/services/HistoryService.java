package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.History;
import ru.gbjava.kinozen.persistence.repositories.HistoryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<History> findHistoryByUserId(UUID id){
        return historyRepository.findAllByIdUser(id).orElseThrow(() -> new RuntimeException("Bad user id"));
    }

    @Transactional
    public History save(History history){
        return historyRepository.save(history);
    }


}
