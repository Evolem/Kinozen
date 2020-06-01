package ru.gbjava.kinozen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.persistence.repositories.DirectorRepository;



@Service
public class DirectorService{
    private DirectorRepository directorRepository;
    private ContentService contentService;

    @Autowired
    public void setDirectorRepository(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
        this.contentService = contentService;
    }


    public Director findById(Long id){
        return directorRepository.findById(id).get();
    }

    public Collection<Content> findByIdDirector(Long id){
        return (Collection<Content>) directorRepository;
    }

    public Director save(Director Director) {
        return directorRepository.save(Director);
    }




}