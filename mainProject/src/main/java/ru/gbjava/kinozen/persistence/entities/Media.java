package ru.gbjava.kinozen.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_media")
public class Media  {

    @Id
    @Column(name = "id_media")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name_media")
    private String name;

    @Column(name = "description_media")
    private String description;

    @Column(name = "released_media")
    private Date released;

    @Column(name = "visible_media")
    private Boolean visible;

    @Column(name = "url_media")
    private String url;

    @ManyToOne
    @JoinTable(name = "tbl_typemedia")
    @JoinColumn(name = "id_typemedia")
    private TypeMedia typemedia;
}
