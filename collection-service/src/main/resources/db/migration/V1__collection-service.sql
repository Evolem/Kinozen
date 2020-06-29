CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table if not exists tbl_collection
(
    id_collection uuid default uuid_generate_v4() not null
        constraint tbl_collection_pk
            primary key,
    name_user varchar(255) not null,
    name_collection varchar(255) not null
);

create unique index if not exists tbl_collection_id_collection_uindex
	on tbl_collection (id_collection);

create table if not exists tbl_content_collection
(
    id_content uuid not null,
    id_collection uuid not null
        constraint tbl_content_collection_tbl_collection_id_collection_fk
            references tbl_collection
);


insert into tbl_collection(id_collection, name_user, name_collection) values ('f97ee60c-a2fd-4a0c-a042-da92554d13eb', 'admin', 'wish');
insert into tbl_content_collection(id_content, id_collection) values ('8d0f77af-0679-4b53-a0ac-5f655c991ef0', 'f97ee60c-a2fd-4a0c-a042-da92554d13eb');
insert into tbl_content_collection(id_content, id_collection) values ('51bf778e-46e5-4f01-8c31-e6bb0de53a7c', 'f97ee60c-a2fd-4a0c-a042-da92554d13eb');
insert into tbl_content_collection(id_content, id_collection) values ('f3b18f94-67f5-43b8-a452-71b62f5e3230', 'f97ee60c-a2fd-4a0c-a042-da92554d13eb');
