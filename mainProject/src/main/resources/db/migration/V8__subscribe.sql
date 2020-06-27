create table if not exists tbl_subscribe_actor
(
    id_user uuid not null
        constraint user___fk
            references tbl_user,
    id_actor uuid not null
        constraint actor___fk
            references tbl_actor
);

create table if not exists tbl_subscribe_genre
(
    id_user uuid not null
        constraint user___fk
            references tbl_user,
    id_genre uuid not null
        constraint genre___fk
            references tbl_genre
);

alter table tbl_episode
    add released_episode date;

