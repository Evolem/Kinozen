package ru.gbjava.collectionservice.persistance.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
