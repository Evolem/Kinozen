package ru.gbjava.collectionservice.persistance.entity;

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

    @ManyToOne
    @JoinColumn(name = "id_collection")
    private Collection collection;
}
