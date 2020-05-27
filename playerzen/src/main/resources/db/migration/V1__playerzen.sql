create table if not exists tbl_mediafile
(
    id_mediafile serial not null
        constraint tbl_mediafile_pk
            primary key,
    id_episode integer not null,
    name_episode varchar(255)
);

alter table tbl_mediafile owner to postgres;
