package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gbjava.kinozen.dto.CommentDto;
import ru.gbjava.kinozen.dto.mappers.CommentMapper;
import ru.gbjava.kinozen.services.CommentService;
import ru.gbjava.kinozen.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.UUID;


@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentFacade;
    private final UserService userService;

    @PostMapping("/addComment")
    public void addComment(@RequestParam String textComment, @RequestParam UUID idEntity,
                           Principal principal, HttpServletResponse response, HttpServletRequest request) throws IOException {

        //todo разгрузить контролер и использовать маппер!
        //todo переделать на offset
        Date date = new Date();
        CommentDto commentDto = new CommentDto();
        commentDto.setIdEntity(idEntity); //беру поля c странице, а не DTO целиком, так как нужен user
        commentDto.setTextComment(textComment);
        commentDto.setUser(userService.findByLogin(principal.getName()));//текущего пользователя берем из principal
        commentDto.setDateComment(date);
        commentFacade.save(CommentMapper.INSTANCE.toEntity(commentDto)); //преобразуем и записываем в Entity
        response.sendRedirect(request.getHeader("referer"));
    }

}