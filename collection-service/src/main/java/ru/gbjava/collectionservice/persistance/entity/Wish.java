package ru.gbjava.collectionservice.persistance.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_wish")
public class Wish {

    @Id
    @Column(name = "id_wish")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Type(type="pg-uuid")
    @Column(name = "id_user")
    private UUID userId;

    @Column(name = "id_content")
    private UUID contentId;

    @Temporal(TemporalType.DATE)
    @Column (name = "added_wishlist")
    private Date added;

}
