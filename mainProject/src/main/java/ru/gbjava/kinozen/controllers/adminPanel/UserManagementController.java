package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.facade.AdminFacade;

import java.util.Map;

@Controller
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class UserManagementController {

    /**
     * Управление пользователями
     */

    private final AdminFacade adminFacade;

    @ModelAttribute("links")
    public Map<String, String> getLinksForMenu() {
        return adminFacade.initLinks();
    }

    @GetMapping
    public String startInfo(Model model) {
        return "adminPanel/users";
    }

}
