package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.persistence.entities.utils.ImageEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_content")
@AttributeOverride(name = "imageName", column = @Column(name = "img_content"))
public class Content extends ImageEntity {

    @Id
    @Column(name = "id_content")
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @Column(name = "name_content")
    private String name;

    @Column(name = "description_content")
    private String description;

    @Column(name = "released_content")
    private Date released;

    @Column(name = "visible_content")
    private Boolean visible;

    @Column(name = "url_content")
    private String url;

    @Column(name = "trailer_link")
    private String trailerLink;

    @Column(name = "type_content")
    @Enumerated(EnumType.ORDINAL)
    private TypeContent type;

    @ManyToMany(mappedBy = "contents")
    Set<Genre> genres;

    @ManyToMany(mappedBy = "contents")
    Set<Actor> actors;

    @ManyToMany(mappedBy = "contents")
    Set<Director> directors;

    @ManyToMany(mappedBy = "likedContent")
    Set<User> likes;

    @ManyToMany(mappedBy = "dislikedContent")
    Set<User> dislikes;

    @ManyToMany(mappedBy = "contentSubscribeList")
    Set<User> contentSubscribers;

    @OneToMany(mappedBy = "content")
    private Set<Season> seasons;
}
