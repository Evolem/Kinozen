package ru.gbjava.kinozen.persistence.entities;

import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_content")
public class Content {

    @Id
    @Column(name = "id_content")
    @GeneratedValue(strategy = IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "id_contenttype")
    private ContentType contentType;

    @ManyToMany(mappedBy = "contents")
    Set<Genre> genres;
}
