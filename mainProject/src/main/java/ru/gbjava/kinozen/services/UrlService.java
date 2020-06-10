package ru.gbjava.kinozen.services;

import org.springframework.lang.NonNull;

public interface UrlService<E> {

    E findByUrl(@NonNull String url);

    void generateAllUrl();
}
