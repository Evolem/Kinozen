alter table tbl_season
	add url_season varchar(255);

create unique index tbl_season_url_season_uindex
	on tbl_season (url_season);

update tbl_season s SET url_season = 'season-1'  where s.id_season = '57d12cd4-ec35-42c1-9b88-22ea330c2b10';
update tbl_season s SET url_season = 'season-2'  where s.id_season = '1b71c4be-5361-4e2c-94e9-93caf73eecaf';
update tbl_season s SET url_season = 'season-3'  where s.id_season = '5e291fb1-6962-4a06-9d82-970d46b3833c';