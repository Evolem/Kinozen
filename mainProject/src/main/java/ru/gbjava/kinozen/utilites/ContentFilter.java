package ru.gbjava.kinozen.utilites;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.repositories.specifications.ContentSpecifications;

import java.util.Map;

@Getter
public class ContentFilter {
    private Specification<Content> spec;
    private final StringBuilder filterDefinition;

    public ContentFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("name") && !map.get("name").isEmpty()) {
            String name = map.get("name");
            spec = spec.or(ContentSpecifications.nameContains(name));
            filterDefinition.append("&name=").append(name);
        }
        if (map.containsKey("name") && !map.get("name").isEmpty()) {
            String name = map.get("name").substring(0, 1).toUpperCase() + map.get("name").substring(1);;
            spec = spec.or(ContentSpecifications.nameContains(name));
            filterDefinition.append("&name=").append(name);
        }
    }
}

