package ru.gbjava.kinozen.persistence.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "tbl_user")
public class User {

    @Id
    @Column(name = "id_user")
    @GeneratedValue
    private UUID id;

    @Column(name = "login_user")
    private String login;

    @Column(name = "password_user")
    private String password;

    @Column(name = "name_user")
    private String name;

    @Column(name = "email_user")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "tbl_role_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "tbl_content_like",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> likedContent;

    @ManyToMany
    @JoinTable(
            name = "tbl_content_dislike",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_content"))
    private Set<Content> dislikedContent;

    @ManyToMany
    @JoinTable(
            name = "tbl_subscribe_genre",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_genre")
    )
    private Set<Genre> genreSubscribeList;

    @ManyToMany
    @JoinTable(
            name = "tbl_subscribe_actor",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_actor")
    )
    private Set<Actor> actorSubscribeList;

    @ManyToMany
    @JoinTable(
            name = "tbl_subscribe_content",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_content")
    )
    private Set<Content> contentSubscribeList;
}
