alter table tbl_content
	add trailer_link text;

create unique index tbl_content_url_content_uindex
	on tbl_content (url_content);