package ru.gbjava.kinozen.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gbjava.kinozen.services.CommentService;
import ru.gbjava.kinozen.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/tempcomment")
@RequiredArgsConstructor
public class CommentController {

    //todo все переделать!

    private final CommentService commentService;
    private final UserService userService;


    @GetMapping
    public String getCommentByUser(Model model, Principal principal) {
//        UserDto userDto = userService.findByLogin(principal.getName());
//        List<CommentDto> comment = commentService.findCommentsByUser(userDto.getId());
//        model.addAttribute("comment", comment);
        return "tempcomment";
    }

    @PostMapping("/add_comment")
    public String addComment(Model model, Principal principal,
                             @RequestParam String usercomment) {
//        UserDto user = userService.findByLogin(principal.getName());
//        System.out.println("id user to add comment: "+user.getId());
//        CommentDto newcomment = new CommentDto(5L,user.getId(),usercomment,new Date());
//        commentService.save(newcomment);
        //newcomment = commentService.save(newcomment);
        return "tempcomment";
    }

}