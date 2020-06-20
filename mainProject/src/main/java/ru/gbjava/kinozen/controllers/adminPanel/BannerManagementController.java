package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
}
