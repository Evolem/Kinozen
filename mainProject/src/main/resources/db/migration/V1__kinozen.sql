create table if not exists tbl_typemedia
(
    id_typemedia   serial       not null
        constraint tbl_typemedia_pk
        primary key,
    name_typemedia varchar(255) not null
);


create table if not exists tbl_director
(
    id_director          serial not null
        constraint tbl_director_pk
        primary key,
    firstname_director   varchar(255),
    lastname_director    varchar(255),
    column_4             integer,
    description_director varchar(255)
);


create table if not exists tbl_media
(
    id_media          serial       not null
        constraint tbl_media_pk
        primary key,
    name_media        varchar(255) not null,
    description_media varchar(255),
    released_media    date,
    visible_media     boolean,
    id_typemedia      integer      not null
        constraint tbl_media_tbl_typemedia_id_typemedia_fk
        references tbl_typemedia,
    url_media         varchar(255)
);


create table if not exists tbl_mediaposter
(
    id_poster  serial  not null
        constraint tbl_mediaposter_pk
        primary key,
    id_media   integer not null
        constraint tbl_mediaposter_tbl_media_id_media_fk
        references tbl_media,
    url_poster integer
);


create table if not exists tbl_genre
(
    id_genre   serial       not null
        constraint tbl_genre_pk
        primary key,
    name_genre varchar(255) not null
);


create table if not exists tbl_genre_media
(
    id_genre integer not null
        constraint tbl_genre_media_tbl_genre_id_genre_fk
        references tbl_genre,
    id_media integer not null
        constraint tbl_genre_media_tbl_media_id_media_fk
        references tbl_media
);


create table if not exists tbl_actor
(
    id_actor          serial not null
        constraint tbl_actor_pk
        primary key,
    firstname_actor   varchar(255),
    lastname_actor    varchar(255),
    description_actor varchar(255)
);



create table if not exists tbl_actor_media
(
    id_actor integer not null
        constraint tbl_actor_media_tbl_actor_id_actor_fk
        references tbl_actor,
    id_media integer not null
        constraint tbl_actor_media_tbl_media_id_media_fk
        references tbl_media
);


create table if not exists tbl_user
(
    id_user       serial       not null
        constraint tbl_user_pk
        primary key,
    login_user    varchar(255) not null,
    password_user varchar(255) not null,
    name_user     varchar(255) not null,
    email_user    varchar(255)
);


create unique index if not exists tbl_user_login_user_uindex
    on tbl_user (login_user);

create table if not exists tbl_role
(
    id_role   serial       not null
        constraint tbl_role_pk
        primary key,
    name_role varchar(255) not null
);


create table if not exists tbl_role_user
(
    id_role integer not null
        constraint tbl_role_user_tbl_role_id_role_fk
        references tbl_role,
    id_user integer not null
        constraint tbl_role_user_tbl_user_id_user_fk
        references tbl_user
);


create table if not exists tbl_history
(
    id_history   serial             not null
        constraint tbl_history_pk
        primary key,
    id_user      integer            not null
        constraint tbl_history_tbl_user_id_user_fk
        references tbl_user,
    id_media     integer            not null
        constraint tbl_history_tbl_media_id_media_fk
        references tbl_media,
    date_history date default now() not null
);


create table if not exists tbl_season
(
    id_season          serial  not null
        constraint tbl_season_pk
        primary key,
    id_media           integer not null
        constraint tbl_season_tbl_media_id_media_fk
        references tbl_media,
    number_season      integer not null,
    description_season varchar(255)
);


create table if not exists tbl_episode
(
    id_episode          serial  not null
        constraint tbl_episode_pk
        primary key,
    id_season           integer not null
        constraint tbl_episode_tbl_season_id_season_fk
        references tbl_season,
    number_episode      integer not null,
    name_episode        varchar(255),
    description_episode varchar(255)
);


create table if not exists tbl_episodeposter
(
    id_episodeposter  serial  not null
        constraint tbl_episodeposter_pk
        primary key,
    id_episode        integer not null
        constraint tbl_episodeposter_tbl_episode_id_episode_fk
        references tbl_episode,
    url_episodeposter varchar(255)
);


create table if not exists tbl_comment
(
    id_comment   serial             not null
        constraint tbl_comment_pk
        primary key,
    id_user      integer            not null
        constraint tbl_comment_tbl_user_id_user_fk
        references tbl_user,
    id_episode   integer            not null
        constraint tbl_comment_tbl_episode_id_episode_fk
        references tbl_episode,
    text_comment varchar(255)       not null,
    date_comment date default now() not null
);


create table if not exists tbl_director_media
(
    id_director integer not null
        constraint tbl_director_media_tbl_director_id_director_fk
        references tbl_director,
    id_media    integer not null
        constraint tbl_director_media_tbl_media_id_media_fk
        references tbl_media
);


insert into tbl_role(id_role, name_role)
values (1, 'ROLE_ADMIN');

insert into tbl_user(id_user, login_user, password_user, name_user)
values (1, 'admin', '$2a$10$5rAOMKmVsh9.NlzXTLLbq.XwouGdg3dwohvb5/HDn692YfdrLthO2', 'vladimir');
insert into tbl_role_user (id_role, id_user)
VALUES (1, 1);

insert into tbl_typemedia(name_typemedia)
values ('Сериал');
insert into tbl_typemedia(name_typemedia)
values ('Фильм');

insert into tbl_media(name_media, description_media, released_media, visible_media, id_typemedia, url_media)
values ('Игра престолов', 'описание 1', '2020-05-27', true, 1, 'igra-prestolov');
insert into tbl_media(name_media, description_media, released_media, visible_media, id_typemedia, url_media)
values ('Игра престолов 2', 'описание 1', '2020-05-17', true, 2, 'igra-prestolov2');
