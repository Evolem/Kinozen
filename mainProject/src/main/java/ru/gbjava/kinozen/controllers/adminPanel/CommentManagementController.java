package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.facade.AdminFacade;

@Controller
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
public class CommentManagementController {

    /**
     * Управление комментариями
     */

    private final AdminFacade adminFacade;

    @GetMapping
    public String startInfo(Model model) {
        adminFacade.initLinks(model);
        return "adminPanel/comments";
    }
}
