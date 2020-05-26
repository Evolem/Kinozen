create table tbl_media
(
	id_media int not null,
	name_media varchar(255) not null,
	description_media varchar(255),
	released_media date,
	id_director int,
	id_typemedia int not null,
	url_media varchar(255)
);

create unique index tbl_media_id_media_uindex
	on tbl_media (id_media);

alter table tbl_media
	add constraint tbl_media_pk
		primary key (id_media);

create table tbl_typemedia
(
	id_typemedia serial not null
		constraint tbl_typemedia_pk
			primary key,
	name_typemedia varchar(255) not null
);

alter table tbl_typemedia owner to postgres;

create unique index tbl_typemedia_name_typemedia_uindex
	on tbl_typemedia (name_typemedia);

