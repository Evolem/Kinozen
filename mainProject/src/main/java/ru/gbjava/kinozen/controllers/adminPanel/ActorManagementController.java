package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gbjava.kinozen.services.facade.AdminFacade;

@Controller
@RequestMapping("/admin/actor")
@RequiredArgsConstructor
public class ActorManagementController {

    /**
     *  Управление актерами
     */

    private final AdminFacade adminFacade;
}
