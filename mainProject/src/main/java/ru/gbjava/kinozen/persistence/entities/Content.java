package ru.gbjava.kinozen.persistence.entities;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tbl_content")
public class Content {

    @Id
    @Column(name = "id_content")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "id_typecontent")
    private TypeContent typeContent;

    @ManyToMany(mappedBy = "contents")
    Set<Genre> genres;
}
