create table if not exists tbl_collection
(
	id_collection uuid not null
		constraint tbl_collection_pk
			primary key,
	id_user uuid not null,
	name_collection varchar(255) not null
);

create unique index if not exists tbl_collection_id_collection_uindex
	on tbl_collection (id_collection);

create table if not exists tbl_content_collection
(
	id_collection uuid not null
		constraint tbl_content_collection_tbl_collection_id_collection_fk
			references tbl_collection,
	id_content uuid not null
);