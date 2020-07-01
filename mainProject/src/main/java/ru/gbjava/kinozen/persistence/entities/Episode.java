package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_episode")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_season")
    private Season season;

    @Column(name = "number_episode")
    private Integer numberEpisode;

    @Column(name = "name_episode")
    private String name;

    @Column(name = "description_episode")
    private String description;

    @Column(name = "added_episode")
    @Temporal(TemporalType.DATE)
    private Date added;

    @Column(name = "img_episode")
    private String imageName;
}
