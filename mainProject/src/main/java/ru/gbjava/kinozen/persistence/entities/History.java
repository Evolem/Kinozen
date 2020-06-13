package ru.gbjava.kinozen.persistence.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "tbl_history")
public class History {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_history")
    private UUID id;

    @Column(name = "id_user")
    private UUID idUser;

    @ManyToOne
    @JoinColumn(name = "id_content")
    private Content content;

    @Column(name = "date_history")
    @Temporal(TemporalType.DATE)
    private Date date;

}
