package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.persistence.entities.Actor;
import ru.gbjava.kinozen.persistence.entities.Content;
import ru.gbjava.kinozen.persistence.entities.Genre;
import ru.gbjava.kinozen.persistence.entities.User;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final ContentService contentService;

    public Set<Content> getContentSubscribeList (User user){
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

}
