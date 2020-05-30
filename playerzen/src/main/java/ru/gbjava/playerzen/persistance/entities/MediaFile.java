package ru.gbjava.playerzen.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_media_file")
public class MediaFile {

    @Id
    @Column(name = "id_media_file")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid_media")
    private UUID media;

    @Column(name = "name_media_file")
    private String nameFile;
}
