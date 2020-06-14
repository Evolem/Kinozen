package ru.gbjava.kinozen.persistence.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;

public class ContentSpecifications {
    public static Specification<Content> nameContains(String word) {
        return (Specification<Content>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+word+"%");
    }
    public static Specification<Content> typeContentEquals(String type) {
        return (Specification<Content>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }
}
