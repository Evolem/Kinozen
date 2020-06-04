package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_film")
public class Film {

    @Id
    @Column(name = "id_film")
    @GeneratedValue(strategy = IDENTITY)
    private UUID uuid_film;
}
