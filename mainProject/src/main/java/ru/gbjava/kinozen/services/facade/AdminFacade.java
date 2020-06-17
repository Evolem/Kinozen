package ru.gbjava.kinozen.services.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.SortedMap;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class AdminFacade {

    public void initLinks(Model model) {
        SortedMap<String, String> links = new TreeMap<>();
        links.put("content", "Contents");
        links.put("seasons", "Seasons");
        model.addAttribute("links",links);
    }
}
