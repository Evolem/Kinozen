package ru.gbjava.collectionservice.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbl_content_collection")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_content")
    private UUID id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_collection")
    private Collection collection;
}
