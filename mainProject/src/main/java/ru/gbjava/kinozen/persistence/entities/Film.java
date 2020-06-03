package ru.gbjava.kinozen.persistence.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//Pozdeyev D.M. Date:02.06.2020
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "tbl_film")
@NoArgsConstructor
@Data
public class Film {
    @Id
    @Column(name = "uuid_film")
    @GeneratedValue(strategy = IDENTITY)
    private UUID uuid_film;




}
