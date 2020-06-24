package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gbjava.kinozen.dto.CommentDto;
import ru.gbjava.kinozen.dto.mappers.CommentMapper;
import ru.gbjava.kinozen.services.UserService;
import ru.gbjava.kinozen.services.facade.CommentFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;


@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentFacade commentFacade;
    private final UserService userService;

    Calendar calendar = Calendar.getInstance();
    long now = calendar.getTimeInMillis();

    @GetMapping
    public String getAllContent(Model model) {
        Iterable<CommentDto> dtoList = CommentMapper.INSTANCE.toDtoList(commentFacade.findAllComment());
        model.addAttribute("commentList", dtoList);
        return "commentAll";
    }

    @PostMapping("/addComment")
    public void addComment(@RequestParam String textComment, @RequestParam UUID idEntity,
                           Principal principal, HttpServletResponse response, HttpServletRequest request) throws IOException {

        Date date = new Date(now);
        CommentDto commentDto = new CommentDto();
        commentDto.setIdEntity(idEntity); //беру поля c странице, а не DTO целиком, так как нужен user
        commentDto.setTextComment(textComment);
        commentDto.setUser(userService.findByLogin(principal.getName()));//текущего пользователя берем из principal
        commentDto.setDateComment(date);
        commentFacade.saveComment(CommentMapper.INSTANCE.toEntity(commentDto)); //преобразуем и записываем в Entity
        response.sendRedirect(request.getHeader("referer"));
    }

}