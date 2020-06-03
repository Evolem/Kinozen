package ru.gbjava.kinozen.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import ru.gbjava.kinozen.dto.UserDto;
import ru.gbjava.kinozen.services.CommentService;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.dto.CommentDto;

import java.security.Principal;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tempcomment")
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public CommentController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping
    public String comment(Model model, Principal principal) {
        UserDto userDto = userService.findByLogin(principal.getName());
        System.out.println("id user: "+userDto.getId());

        List<CommentDto> comment = commentService.findCommentsByUser(userDto.getId());
        model.addAttribute("comment", comment);
        return "tempcomment";
}

    @PostMapping("/add_comment")
    public String add_comment(Model model,Principal principal,
                             @RequestParam String usercomment){
        UserDto user = userService.findByLogin(principal.getName());
        System.out.println("id user to add comment: "+user.getId());
        CommentDto newcomment = new CommentDto(5L,user.getId(),usercomment,new Date());

    //    commentService.save(newcomment);

        //newcomment = commentService.save(newcomment);

        return "tempcomment";
    }

}