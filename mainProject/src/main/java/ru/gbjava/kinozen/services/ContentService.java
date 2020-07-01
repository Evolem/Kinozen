package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.persistence.repositories.ContentRepository;
import ru.gbjava.kinozen.utilites.StringConverter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContentService implements CrudService<Content, UUID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final ContentRepository contentRepository;

    @Override
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    public List<Content> findAllSerials() {
        return contentRepository.findAllByType(TypeContent.SERIAL);
    }

    public List<Content> findAllFilms() {
        return contentRepository.findAllByType(TypeContent.FILM);
    }

    @Override
    public Content findById(@NonNull UUID uuid) {
        return contentRepository.findById(uuid).orElseThrow(() -> new RuntimeException("Content not found! " + uuid));
    }

    @Override
    @Transactional
    public Content save(Content content) {
        content.setUrl(StringConverter.cyrillicToLatin(content.getName()));
        return contentRepository.save(content);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull UUID uuid) {
        contentRepository.deleteById(uuid);
    }

    public Content findByUrl(String url) {
        return contentRepository.findContentByUrl(url).orElseThrow(() -> new RuntimeException("Content not found! " + url));
    }

    @Transactional
    public void reGenerateAllUrl() {
        List<Content> contents = contentRepository.findAll();
        for (Content c : contents) {
            c.setUrl(StringConverter.cyrillicToLatin(c.getName()));
            contentRepository.save(c);
        }
    }

    //TODO: пересмотреть логику, это точно bad practices
    public List<Content> findWishContents(List<UUID> contentList) {
        return contentList.stream().map(content -> contentRepository.findById(content).orElseThrow()).collect(Collectors.toList());


    }

    public List<Content> findAll(String name, Date releasedFrom, Date releasedTo, Boolean visible, Integer typeOrdinal) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Content> criteriaQuery = criteriaBuilder.createQuery(Content.class);
        Root<Content> root = criteriaQuery.from(Content.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));

        }

        if (releasedFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("released"), releasedFrom));
        }

        if (releasedTo != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("released"), releasedTo));
        }

        if (visible != null) {
            if (visible) {
                predicates.add(criteriaBuilder.isTrue(root.get("visible")));
            } else {
                predicates.add(criteriaBuilder.isFalse(root.get("visible")));
            }
        }

        if (typeOrdinal != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), typeOrdinal));
        }

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{})));

        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    public void changeVisible(UUID uuid) {
        Optional<Content> optionalContent = contentRepository.findById(uuid);
        if (optionalContent.isPresent()) {
            Content content = optionalContent.get();
            content.setVisible(!content.getVisible());
            contentRepository.save(content);
        }


    }

    public Set<Content> getNewContents(){
        return contentRepository.getNewContents();
    }

    public List<Content> findAllFilmsByGenre(Genre genre) {
        return contentRepository.findAllByTypeAndGenres(TypeContent.FILM, genre);
    }

    public List<Content> findAllSerialsByGenre(Genre genre) {
        return contentRepository.findAllByTypeAndGenres(TypeContent.SERIAL, genre);
    }

    public Page<Content> findAll(Specification<Content> spec, Pageable pageable) {
        return contentRepository.findAll(spec, pageable);
    }


}
