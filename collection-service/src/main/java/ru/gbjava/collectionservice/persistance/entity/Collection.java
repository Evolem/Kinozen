package ru.gbjava.collectionservice.persistance.entity;

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
@Table(name = "tbl_collection")
public class Collection {

    @Id
    @Column(name = "id_collection")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name_user")
    private String user;

    @Column(name = "name_collection")
    private String name;

}
