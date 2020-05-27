package ru.gbjava.playerzen.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_mediafile")
public class MediaFile {

    @Id
    private Long id;
    @Column(name = "name_episode")
    private String name;
}
