package ru.gbjava.collectionservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbjava.collectionservice.dto.WishDto;
import ru.gbjava.collectionservice.persistance.entity.Wish;
import ru.gbjava.collectionservice.service.WishListService;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wish")
public class WishListController {

    private final WishListService wishListService;

    @GetMapping(value = "/list/{userId}")
    public ResponseEntity<List<Wish>> getWishList(@PathVariable UUID userId) {
        List<Wish> wishList = wishListService.getWishListByUserId(userId);
        return ResponseEntity.ok(wishList);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<WishDto> addContentToCollection(@RequestBody WishDto wishDto) throws ParseException {
        wishListService.save(wishDto);
        return ResponseEntity.ok(wishDto);
    }

    @DeleteMapping(value = "/delete/{wishId}")
    public void deleteWishContent(@PathVariable UUID wishId) {
        wishListService.deleteById(wishId);
    }

}
