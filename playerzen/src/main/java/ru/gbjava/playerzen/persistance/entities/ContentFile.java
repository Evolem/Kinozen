package ru.gbjava.playerzen.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_media_file")
public class ContentFile {

    @Id
    @Column(name = "id_media_file")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid_media")
    private UUID content;

    @Column(name = "name_media_file")
    private String nameFile;
}
