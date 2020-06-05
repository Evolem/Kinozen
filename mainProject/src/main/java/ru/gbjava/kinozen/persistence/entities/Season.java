package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_season")
    private UUID id;

    @Column(name = "id_content")
    @ManyToOne
    private Content content;

    @Column(name = "number_season")
    private Integer numberSeason;

    @Column(name = "description_season")
    private String description;

    @OneToMany(mappedBy = "season")
    private List<Episode> episodes;
}
