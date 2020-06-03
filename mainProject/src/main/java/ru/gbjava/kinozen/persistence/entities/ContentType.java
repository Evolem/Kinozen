package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_typecontent")
public class ContentType {

    @Id
    @Column(name = "id_typecontent")
    @GeneratedValue(strategy = IDENTITY)
    private UUID id;

    @Column(name = "name_typecontent")
    private String name;

    @OneToMany(mappedBy = "contentType")
    private List<Content> allContent;
}
