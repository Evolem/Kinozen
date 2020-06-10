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

create unique index tbl_media_file_name_media_file_uindex
    on tbl_media_file (name_media_file);

create unique index tbl_media_file_uuid_media_uindex
    on tbl_media_file (uuid_media);

insert into tbl_media_file (uuid_media, name_media_file)
values ('86a38fc4-a9a6-45e8-a6c8-08aac7949f25', '1.mp4')