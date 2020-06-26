package ru.gbjava.collectionservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.collectionservice.dto.WishDto;
import ru.gbjava.collectionservice.persistance.entity.Wish;
import ru.gbjava.collectionservice.persistance.repository.WishListRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    public  final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<Wish> getWishListByUserId(UUID userId) {
        return wishListRepository.findAllByUserId(userId).orElse(new ArrayList<>());
    }

    public void deleteById(UUID id) {
        wishListRepository.deleteById(id);
    }

    public Wish save(WishDto wishDto) throws ParseException {
        Date date = simpleDateFormat.parse(wishDto.getAdded());
        Wish wish = Wish.builder()
                .userId(UUID.fromString(wishDto.getUserId()))
                .contentId(UUID.fromString(wishDto.getContentId()))
                .added(date)
                .build();
        return wishListRepository.save(wish);
    }
}
