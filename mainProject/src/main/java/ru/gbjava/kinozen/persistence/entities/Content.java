package ru.gbjava.kinozen.persistence.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gbjava.kinozen.persistence.entities.enums.TypeContent;
import ru.gbjava.kinozen.persistence.entities.utils.ImageEntity;

import javax.persistence.*;
import java.net.URL;
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
@ApiModel(description = "Entity-класс для контента (фильм/сериал).")
public class Content extends ImageEntity {

    @Id
    @Column(name = "id_content")
    @GeneratedValue(strategy = AUTO)
    private UUID id;

    @ApiModelProperty(required = true, value = "Название контента")
    @Column(name = "name_content")
    private String name;

    @ApiModelProperty(required = true, value = "Описание контента")
    @Column(name = "description_content")
    private String description;

    @ApiModelProperty(required = true, value = "Дата выхода контента")
    @Column(name = "released_content")
    private Date released;

    @ApiModelProperty(required = true, value = "Видимость контента для пользователя")
    @Column(name = "visible_content")
    private Boolean visible;

    @ApiModelProperty(required = true, value = "Url котента (по нему создаётся url)")
    @Column(name = "url_content")
    private String url;

    @ApiModelProperty(required = true, value = "Ссылка для трейлера")
    @Column(name = "trailer_link")
    private String trailerLink;

    @ApiModelProperty(required = true, value = "Тип контента: Фильм/Сериал")
    @Column(name = "type_content")
    @Enumerated(EnumType.ORDINAL)
    private TypeContent type;

    @ApiModelProperty(required = true, value = "Жанры контента")
    @ManyToMany(mappedBy = "contents")
    Set<Genre> genres;

    @ApiModelProperty(required = true, value = "Актёры засветившиеся в контенте")
    @ManyToMany(mappedBy = "contents")
    Set<Actor> actors;

    @ApiModelProperty(required = true, value = "Режисёры контента")
    @ManyToMany(mappedBy = "contents")
    Set<Director> directors;

    @ApiModelProperty(required = true, value = "Множество лайков")
    @ManyToMany(mappedBy = "likedContent")
    Set<User> likes;

    @ApiModelProperty(required = true, value = "Множество диздайков")
    @ManyToMany(mappedBy = "dislikedContent")
    Set<User> dislikes;
}
