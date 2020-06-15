package ru.gbjava.collectionservice.persistance.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbl_collection")
public class Collection {

    @Id
    @Column(name = "id_collection")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "id_user")
    private UUID user;

    @Column(name = "name_collection")
    private String name;

    @OneToMany(mappedBy = "collection")
    private List<Content> contentList;
}
