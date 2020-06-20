package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.facade.AdminFacade;

@Controller
@RequestMapping("/admin/director")
@RequiredArgsConstructor
public class DirectorManagementController {

    /**
     *  Управление режиссерами
     */

    private final AdminFacade adminFacade;
}
