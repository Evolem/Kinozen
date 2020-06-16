alter table tbl_actor_content drop constraint tbl_actor_content_tbl_actor_id_actor_fk;

alter table tbl_actor_content
	add constraint tbl_actor_content_tbl_actor_id_actor_fk
		foreign key (id_actor) references tbl_actor
			on delete cascade;

alter table tbl_actor_content drop constraint tbl_actor_content_tbl_media_id_content_fk;

alter table tbl_actor_content
	add constraint tbl_actor_content_tbl_media_id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

alter table tbl_comment drop constraint tbl_comment_tbl_user_id_user_fk;

alter table tbl_comment
	add constraint tbl_comment_tbl_user_id_user_fk
		foreign key (id_user) references tbl_user
			on delete cascade;

alter table tbl_director_content drop constraint tbl_director_content_tbl_director_id_director_fk;

alter table tbl_director_content
	add constraint tbl_director_content_tbl_director_id_director_fk
		foreign key (id_director) references tbl_director
			on delete cascade;

alter table tbl_director_content drop constraint tbl_director_content_tbl_content_id_content_fk;

alter table tbl_director_content
	add constraint tbl_director_content_tbl_content_id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

alter table tbl_episode drop constraint tbl_episode_tbl_season_id_season_fk;

alter table tbl_episode
	add constraint tbl_episode_tbl_season_id_season_fk
		foreign key (id_season) references tbl_season
			on delete cascade;

alter table tbl_genre_content drop constraint tbl_genre_content_tbl_genre_id_genre_fk;

alter table tbl_genre_content
	add constraint tbl_genre_content_tbl_genre_id_genre_fk
		foreign key (id_genre) references tbl_genre
			on delete cascade;

alter table tbl_genre_content drop constraint tbl_genre_content_tbl_content_id_content_fk;

alter table tbl_genre_content
	add constraint tbl_genre_content_tbl_content_id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

alter table tbl_history drop constraint tbl_history_tbl_user_id_user_fk;

alter table tbl_history
	add constraint tbl_history_tbl_user_id_user_fk
		foreign key (id_user) references tbl_user
			on delete cascade;

alter table tbl_history drop constraint tbl_history_tbl_content_id_content_fk;

alter table tbl_history
	add constraint tbl_history_tbl_content_id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

alter table tbl_role_user drop constraint tbl_role_user_tbl_user_id_user_fk;

alter table tbl_role_user
	add constraint tbl_role_user_tbl_user_id_user_fk
		foreign key (id_user) references tbl_user
			on delete cascade;

alter table tbl_season drop constraint tbl_season_tbl_content_id_content_fk;

alter table tbl_season
	add constraint tbl_season_tbl_content_id_content_fk
		foreign key (id_content) references tbl_content
			on delete cascade;

