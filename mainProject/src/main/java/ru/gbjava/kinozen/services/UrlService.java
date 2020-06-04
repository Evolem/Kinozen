package ru.gbjava.kinozen.services;

public interface UrlService <E>{
    E findByUrl(String url);
    void generateAllUrl();
}
