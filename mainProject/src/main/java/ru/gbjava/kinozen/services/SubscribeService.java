package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.entities.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final ContentService contentService;
    private final UserService userService;

    public Set<Content> getContentSubscribeList (String login){
        User user = userService.findByLogin(login);
        Set<Content> contentSubscribeList = new HashSet<>();
        for(Content newContent: contentService.getNewContents()){
            for(Genre newContentGenre: newContent.getGenres()){
                for(Genre subscribeGenre: user.getGenreSubscribeList()){
                    if(newContentGenre.equals(subscribeGenre)){
                        contentSubscribeList.add(newContent);
                    }
                }
            }
            for(Actor newContentActor: newContent.getActors()){
                for(Actor subscribeActor: user.getActorSubscribeList()){
                    if(newContentActor.equals(subscribeActor)){
                        contentSubscribeList.add(newContent);
                    }
                }
            }
        }
        return contentSubscribeList;
    }

    @Transactional
    public void subscribeUserToGenre(String login, Genre genre) {
        User user = userService.findByLogin(login);
        Set<Genre> userGenreSubscribeList = user.getGenreSubscribeList();

        if (userGenreSubscribeList.contains(genre)) {
            userGenreSubscribeList.remove(genre);
        } else {
            userGenreSubscribeList.add(genre);
        }
        user.setGenreSubscribeList(userGenreSubscribeList);
        userService.save(user);
    }

    @Transactional
    public void subscribeUserToActor(String login, Actor actor){
        User user = userService.findByLogin(login);
        Set<Actor> userActorSubscribeList = user.getActorSubscribeList();

        if (userActorSubscribeList.contains(actor)) {
            userActorSubscribeList.remove(actor);
        } else {
            userActorSubscribeList.add(actor);
        }
        user.setActorSubscribeList(userActorSubscribeList);
        userService.save(user);
    }

    public boolean isUserSubscribedToGenre(String login, Genre genre){
        User user = userService.findByLogin(login);
        return user.getGenreSubscribeList().contains(genre);
    }

    public boolean isUserSubscribedToActor(String login, Actor actor){
        User user = userService.findByLogin(login);
        return user.getActorSubscribeList().contains(actor);
    }

}
