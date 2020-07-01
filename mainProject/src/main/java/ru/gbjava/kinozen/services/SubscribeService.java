package ru.gbjava.kinozen.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gbjava.kinozen.dto.ActorDto;
import ru.gbjava.kinozen.dto.GenreDto;
import ru.gbjava.kinozen.dto.mappers.ActorMapper;
import ru.gbjava.kinozen.dto.mappers.GenreMapper;
import ru.gbjava.kinozen.persistence.entities.*;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final int DAYS = 7;

    private final ContentService contentService;
    private final UserService userService;

    private Optional<Season> getLastSeason(Set<Season> seasons){
        int maxNumber = 0;
        Season lastSeason = null;
        for(Season s: seasons){
            if(maxNumber < s.getNumberSeason()){
                maxNumber = s.getNumberSeason();
                lastSeason = s;
            }
        }
        return Optional.ofNullable(lastSeason);
    }

    public Set<Content> getContentSubscribeList (String login){
        Set<Content> contentSubscribeList = new HashSet<>();
        contentSubscribeList.addAll(getContentSubscribeListByActor(login));
        contentSubscribeList.addAll(getContentSubscribeListByGenre(login));
        return contentSubscribeList;
    }

    public Set<Content> getContentSubscribeListByActor (String login){
        User user = userService.findByLogin(login);
        Set<Content> contentSubscribeListByActor = new HashSet<>();
        for(Content newContent: contentService.getNewContents()){
            for(Actor newContentActor: newContent.getActors()){
                for(Actor subscribeActor: user.getActorSubscribeList()){
                    if(newContentActor.equals(subscribeActor)){
                        contentSubscribeListByActor.add(newContent);
                    }
                }
            }
        }
        return contentSubscribeListByActor;
    }

    public Set<Content> getContentSubscribeListByGenre (String login){
        User user = userService.findByLogin(login);
        Set<Content> contentSubscribeListByGenre = new HashSet<>();
        for(Content newContent: contentService.getNewContents()){
            for(Genre newContentGenre: newContent.getGenres()){
                for(Genre subscribeGenre: user.getGenreSubscribeList()){
                    if(newContentGenre.equals(subscribeGenre)){
                        contentSubscribeListByGenre.add(newContent);
                    }
                }
            }
        }
        return contentSubscribeListByGenre;
    }

    public List<Episode> getEpisodeSubscribeList(String login) throws Exception {
        User user = userService.findByLogin(login);
        List<Episode> newEpisodes = new ArrayList<>();
        Date date = new Date();
        date.setTime(date.getTime() - (DAYS + 1) * 24 * 3600 *1000);

        for(Content c: user.getContentSubscribeList()){
            Season lastSeason = getLastSeason(c.getSeasons()).orElseThrow(() -> new Exception("No such season found"));
            for (Episode e: lastSeason.getEpisodes()){
                if(date.before(e.getAdded())){
                    newEpisodes.add(e);
                }
            }
        }
        return  newEpisodes;
    }

    public boolean isUserSubscribedToGenre(String login, GenreDto genreDto){
        User user = userService.findByLogin(login);
        return user.getGenreSubscribeList().contains(GenreMapper.INSTANCE.toEntity(genreDto));
    }

    public boolean isUserSubscribedToActor(String login, ActorDto actorDto){
        User user = userService.findByLogin(login);
        return user.getActorSubscribeList().contains(ActorMapper.INSTANCE.toEntity(actorDto));
    }

    public boolean isUserSubscribedToContent(String login, Content content){
        User user = userService.findByLogin(login);
        return user.getContentSubscribeList().contains(content);
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

    @Transactional
    public void subscribeUserToContent(String login, Content content){
        User user = userService.findByLogin(login);
        Set<Content> userContentSubscribeList = user.getContentSubscribeList();

        if (userContentSubscribeList.contains(content)) {
            userContentSubscribeList.remove(content);
        } else {
            userContentSubscribeList.add(content);
        }
        user.setContentSubscribeList(userContentSubscribeList);
        userService.save(user);
    }

}
