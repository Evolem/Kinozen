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

create table tbl_subscribe_content
(
    id_user uuid not null
        constraint user___fk
            references tbl_user,
    id_content uuid not null
        constraint content___fk
            references tbl_content
);




