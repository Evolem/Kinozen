create table if not exists tbl_media_file
(
    id_media_file bigserial not null
        constraint tbl_media_file_pk
            primary key,
    uuid_media uuid not null,
    name_media_file varchar(255) not null
);

create unique index if not exists tbl_media_file_id_media_file_uindex
    on tbl_media_file (id_media_file);

