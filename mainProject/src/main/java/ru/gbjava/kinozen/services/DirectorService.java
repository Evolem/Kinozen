package ru.gbjava.kinozen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.gbjava.kinozen.persistence.entities.Director;
import ru.gbjava.kinozen.persistence.entities.Media;
import ru.gbjava.kinozen.persistence.repositories.DirectorRepository;



@Service
public class DirectorService{
    private DirectorRepository directorRepository;

    @Autowired
    public void setDirectorRepository(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    public Director findById(Long id){
        return directorRepository.findById(id).get();
    }

    public Collection<Media> findByIdDirector(Long id){
        return (Collection<Media>) directorRepository;
    }

    public Director save(Director Director) {
        return directorRepository.save(Director);
    }
}