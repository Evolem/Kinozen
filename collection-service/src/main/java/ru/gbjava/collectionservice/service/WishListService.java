package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.persistance.entity.Wish;
import ru.gbjava.collectionservice.persistance.repository.WishListRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishListRepository wishListRepository;

    public List<Wish> getWishListByUserId(UUID userId){
        return wishListRepository.findAllByUserId(userId).orElse(new ArrayList<>());
    }

    public void deleteById(UUID id) {
        wishListRepository.deleteById(id);
    }
}
