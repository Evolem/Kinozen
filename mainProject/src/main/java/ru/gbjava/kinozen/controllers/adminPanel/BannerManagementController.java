package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.facade.AdminFacade;

@Controller
@RequestMapping("/admin/banner")
@RequiredArgsConstructor
public class BannerManagementController {

    /**
     *  Управление баннерами для слайдов на главной странице
     */

    private final AdminFacade adminFacade;

    @GetMapping
    public String startInfo(Model model) {
        adminFacade.initLinks(model);
        return "adminPanel/banners";
    }
}
