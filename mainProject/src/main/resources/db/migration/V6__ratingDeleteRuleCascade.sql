alter table tbl_content_like drop constraint id_user_fk;

alter table tbl_content_like
	add constraint id_user_fk
		foreign key (id_user) references tbl_user
			on delete cascade;

alter table tbl_content_like drop constraint id_content_fk;

alter table tbl_content_like
	add constraint id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

alter table tbl_season alter column url_season set not null;

drop index tbl_season_url_season_uindex;

