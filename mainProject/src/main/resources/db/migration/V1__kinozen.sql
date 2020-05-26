create table if not exists tbl_typemedia
(
    id_typemedia serial not null
        constraint tbl_typemedia_pk
            primary key,
    name_typemedia varchar(255) not null
);

alter table tbl_typemedia owner to postgres;

create table if not exists tbl_director
(
    id_director serial not null
        constraint tbl_director_pk
            primary key,
    firstname_director varchar(255),
    lastname_director varchar(255),
    column_4 integer,
    description_director varchar(255)
);

alter table tbl_director owner to postgres;

create table if not exists tbl_media
(
    id_media serial not null
        constraint tbl_media_pk
            primary key,
    name_media varchar(255) not null,
    description_media varchar(255),
    released_media date,
    visible_media boolean,
    id_director integer
        constraint tbl_media_tbl_director_id_director_fk
            references tbl_director,
    id_typemedia integer not null
        constraint tbl_media_tbl_typemedia_id_typemedia_fk
            references tbl_typemedia,
    url_media varchar(255)
);

alter table tbl_media owner to postgres;

create table if not exists tbl_mediaposter
(
    id_poster serial not null
        constraint tbl_mediaposter_pk
            primary key,
    id_media integer not null
        constraint tbl_mediaposter_tbl_media_id_media_fk
            references tbl_media,
    url_poster integer
);

alter table tbl_mediaposter owner to postgres;

create table if not exists tbl_genre
(
    id_genre serial not null
        constraint tbl_genre_pk
            primary key,
    name_genre varchar(255) not null
);

alter table tbl_genre owner to postgres;

create table if not exists tbl_genre_media
(
    id_genre integer not null
        constraint tbl_genre_media_tbl_genre_id_genre_fk
            references tbl_genre,
    id_media integer not null
        constraint tbl_genre_media_tbl_media_id_media_fk
            references tbl_media
);

alter table tbl_genre_media owner to postgres;

create table if not exists tbl_actor
(
    id_actor serial not null
        constraint tbl_actor_pk
            primary key,
    firstname_actor varchar(255),
    lastname_actor varchar(255),
    description_actor varchar(255)
);

alter table tbl_actor owner to postgres;

create table if not exists tbl_actor_media
(
    id_actor integer not null
        constraint tbl_actor_media_tbl_actor_id_actor_fk
            references tbl_actor,
    id_media integer not null
        constraint tbl_actor_media_tbl_media_id_media_fk
            references tbl_media
);

alter table tbl_actor_media owner to postgres;

create table if not exists tbl_user
(
    id_user serial not null
        constraint tbl_user_pk
            primary key,
    login_user varchar(255) not null,
    password_user varchar(255) not null,
    name_user varchar(255) not null,
    email_user varchar(255)
);

alter table tbl_user owner to postgres;

create unique index if not exists tbl_user_login_user_uindex
    on tbl_user (login_user);

create table if not exists tbl_role
(
    id_role serial not null
        constraint tbl_role_pk
            primary key,
    name_role varchar(255) not null
);

alter table tbl_role owner to postgres;

create table if not exists tbl_role_user
(
    id_role integer not null
        constraint tbl_role_user_tbl_role_id_role_fk
            references tbl_role,
    id_user integer not null
        constraint tbl_role_user_tbl_user_id_user_fk
            references tbl_user
);

alter table tbl_role_user owner to postgres;

create table if not exists tbl_history
(
    id_history serial not null
        constraint tbl_history_pk
            primary key,
    id_user integer not null
        constraint tbl_history_tbl_user_id_user_fk
            references tbl_user,
    id_media integer not null
        constraint tbl_history_tbl_media_id_media_fk
            references tbl_media
);

alter table tbl_history owner to postgres;

create table if not exists tbl_season
(
    id_season serial not null
        constraint tbl_season_pk
            primary key,
    id_media integer not null
        constraint tbl_season_tbl_media_id_media_fk
            references tbl_media,
    number_season integer not null,
    description_season varchar(255)
);

alter table tbl_season owner to postgres;

create table if not exists tbl_episode
(
    id_episode serial not null
        constraint tbl_episode_pk
            primary key,
    id_season integer not null
        constraint tbl_episode_tbl_season_id_season_fk
            references tbl_season,
    number_episode integer not null,
    name_episode varchar(255),
    description_episode varchar(255)
);

alter table tbl_episode owner to postgres;

create table if not exists tbl_episodeposter
(
    id_episodeposter serial not null
        constraint tbl_episodeposter_pk
            primary key,
    id_episode integer not null
        constraint tbl_episodeposter_tbl_episode_id_episode_fk
            references tbl_episode,
    url_episodeposter varchar(255)
);

alter table tbl_episodeposter owner to postgres;

create table if not exists tbl_comment
(
    id_commtent serial not null
        constraint tbl_comment_pk
            primary key,
    id_user integer not null
        constraint tbl_comment_tbl_user_id_user_fk
            references tbl_user,
    id_episode integer not null
        constraint tbl_comment_tbl_episode_id_episode_fk
            references tbl_episode,
    date_commtent date,
    text_commtent varchar(255) not null
);

alter table tbl_comment owner to postgres;

