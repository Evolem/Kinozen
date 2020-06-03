CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
create table if not exists flyway_schema_history
(
    installed_rank uuid default uuid_generate_v4() not null
        constraint flyway_schema_history_pk
            primary key,
    version varchar(50),
    description varchar(200) not null,
    type varchar(20) not null,
    script varchar(1000) not null,
    checksum integer,
    installed_by varchar(100) not null,
    installed_on timestamp default now() not null,
    execution_time integer not null,
    success boolean not null
);



create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table if not exists tbl_contenttype
(
    id_contenttype uuid default uuid_generate_v4() not null
        constraint tbl_contenttype_pk
            primary key,
    name_contenttype varchar(255) not null
);



create table if not exists tbl_director
(
    id_director uuid default uuid_generate_v4() not null
        constraint tbl_director_pk
            primary key,
    firstname_director varchar(255),
    lastname_director varchar(255),
    description_director varchar(255),
    img_director varchar(255)
);



create table if not exists tbl_content
(
    id_content uuid default uuid_generate_v4() not null
        constraint tbl_content_pk
            primary key,
    name_content varchar(255) not null,
    description_content varchar(255),
    released_content date,
    visible_content boolean,
    id_contenttype uuid not null
        constraint tbl_content_tbl_contenttype_id_contenttype_fk
        references tbl_contenttype,
    url_content varchar(255),
    img_content varchar(255)
);



create table if not exists tbl_genre
(
    id_genre uuid default uuid_generate_v4() not null
        constraint tbl_genre_pk
            primary key,
    name_genre varchar(255) not null,
	url_genre varchar(255)
);



create table if not exists tbl_genre_content
(
    id_genre uuid default uuid_generate_v4() not null
        constraint tbl_genre_content_tbl_genre_id_genre_fk
        references tbl_genre,
    id_content uuid default uuid_generate_v4() not null
        constraint tbl_genre_content_tbl_content_id_content_fk
        references tbl_content
);



create table if not exists tbl_actor
(
    id_actor uuid default uuid_generate_v4() not null
        constraint tbl_actor_pk
        primary key,
    firstname_actor varchar(255),
    lastname_actor varchar(255),
    description_actor varchar(255),
    img_actor varchar(255)
);



create table if not exists tbl_actor_content
(
    id_actor uuid default uuid_generate_v4() not null
        constraint tbl_actor_content_tbl_actor_id_actor_fk
        references tbl_actor,
    id_content uuid default uuid_generate_v4() not null
        constraint tbl_actor_content_tbl_media_id_content_fk
        references tbl_content
);



create table if not exists tbl_user
(
    id_user uuid default uuid_generate_v4() not null
        constraint tbl_user_pk
        primary key,
    login_user varchar(255) not null,
    password_user varchar(255) not null,
    name_user varchar(255) not null,
    email_user varchar(255)
);



create unique index if not exists tbl_user_login_user_uindex
    on tbl_user (login_user);

create table if not exists tbl_role
(
    id_role uuid default uuid_generate_v4() not null
        constraint tbl_role_pk
        primary key,
    name_role varchar(255) not null
);



create table if not exists tbl_role_user
(
    id_role uuid default uuid_generate_v4() not null
        constraint tbl_role_user_tbl_role_id_role_fk
        references tbl_role,
    id_user uuid default uuid_generate_v4() not null
        constraint tbl_role_user_tbl_user_id_user_fk
        references tbl_user
);



create table if not exists tbl_history
(
    id_history uuid default uuid_generate_v4() not null
        constraint tbl_history_pk
        primary key,
    id_user uuid not null
        constraint tbl_history_tbl_user_id_user_fk
        references tbl_user,
    id_content uuid not null
        constraint tbl_history_tbl_content_id_content_fk
        references tbl_content (id_content),
    date_history date default now() not null
);



create table if not exists tbl_season
(
    id_season uuid default uuid_generate_v4() not null
        constraint tbl_season_pk
        primary key,
    id_content uuid not null
        constraint tbl_season_tbl_content_id_content_fk
        references tbl_content (id_content),
    number_season integer not null,
    description_season varchar(255)
);



create table if not exists tbl_comment
(
    id_comment uuid default uuid_generate_v4() not null
        constraint tbl_comment_pk
        primary key,
    id_user uuid not null
        constraint tbl_comment_tbl_user_id_user_fk
        references tbl_user,
    id_entity integer not null,
    text_comment varchar(255) not null,
    date_comment date default now() not null
);


create table if not exists tbl_director_content
(
    id_director uuid default uuid_generate_v4() not null
        constraint tbl_director_content_tbl_director_id_director_fk
        references tbl_director,
    id_content uuid default uuid_generate_v4() not null
        constraint tbl_director_content_tbl_content_id_content_fk
        references tbl_content (id_content)
);


create table if not exists tbl_episode
(
    id_episode uuid default uuid_generate_v4() not null
        constraint tbl_episode_pk
        primary key,
    id_season uuid not null
        constraint tbl_episode_tbl_season_id_season_fk
        references tbl_season,
    number_episode integer not null,
    name_episode varchar(255) not null,
    description_episode varchar(255),
    img_episode varchar(255)
);


create table if not exists tbl_film
(
    id_film uuid default uuid_generate_v4() not null
        constraint tbl_film_pk
        primary key,
    id_content uuid default uuid_generate_v4() not null
        constraint tbl_film_tbl_content_id_content_fk
        references tbl_content (id_content)
);


-- Добавление ролей
insert into tbl_role(id_role, name_role)
values ('30cd0855-daa0-4611-8b9b-2b91b9defdde', 'ROLE_ADMIN');
insert into tbl_role(id_role, name_role)
values ('06ff9230-c3dd-44bb-aaf3-ef2ef4991c3f', 'ROLE_USER');

-- Добавление админа (admin admin)
insert into tbl_user(id_user, login_user, password_user, name_user)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', 'admin', '$2a$10$5rAOMKmVsh9.NlzXTLLbq.XwouGdg3dwohvb5/HDn692YfdrLthO2', 'vladimir');

-- Связка пользователя с ролью
insert into tbl_role_user (id_role, id_user)
values ('30cd0855-daa0-4611-8b9b-2b91b9defdde', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089');

-- Заполнение типа контента
insert into tbl_contenttype(id_contenttype, name_contenttype)
values ('7354d2dd-c12e-45cf-b1a5-774469eb4d8a', 'Сериал');
insert into tbl_contenttype(id_contenttype, name_contenttype)
values ('aeaf93f8-d8fc-4cfa-ad66-7b685ced73b4', 'Фильм');

-- Заполнение таблице content
insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Игра престолов', 'тут описание', '2020-05-27', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Пустыня смерти', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Сопрано', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Рик и морти', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Мир дикого запада', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Убивая Еву', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Шепот', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

insert into tbl_content(name_content, description_content, released_content, visible_content, id_contenttype)
values ('Южный парк', 'тут описание', '2020-05-17', true, '7354d2dd-c12e-45cf-b1a5-774469eb4d8a');

-- Заполенение Жанров
insert into tbl_genre(name_genre) values ('аниме');
insert into tbl_genre(name_genre) values ('биографический');
insert into tbl_genre(name_genre) values ('боевик');
insert into tbl_genre(name_genre) values ('вестерн');
insert into tbl_genre(name_genre) values ('военный');
insert into tbl_genre(name_genre) values ('детектив');
insert into tbl_genre(name_genre) values ('детский');
insert into tbl_genre(name_genre) values ('документальный');
insert into tbl_genre(name_genre) values ('драма');
insert into tbl_genre(name_genre) values ('исторический');
insert into tbl_genre(name_genre) values ('кинокомикс');
insert into tbl_genre(name_genre) values ('комедия');
insert into tbl_genre(name_genre) values ('концерт');
insert into tbl_genre(name_genre) values ('короткометражный');
insert into tbl_genre(name_genre) values ('криминал');
insert into tbl_genre(name_genre) values ('мелодрама');
insert into tbl_genre(name_genre) values ('мистика');
insert into tbl_genre(name_genre) values ('музыка');
insert into tbl_genre(name_genre) values ('мультфильм');
insert into tbl_genre(name_genre) values ('мюзикл');
insert into tbl_genre(name_genre) values ('научный');
insert into tbl_genre(name_genre) values ('приключения');
insert into tbl_genre(name_genre) values ('реалити-шоу');
insert into tbl_genre(name_genre) values ('семейный');
insert into tbl_genre(name_genre) values ('спорт');
insert into tbl_genre(name_genre) values ('ток-шоу');
insert into tbl_genre(name_genre) values ('триллер');
insert into tbl_genre(name_genre) values ('ужасы');
insert into tbl_genre(name_genre) values ('фантастика');
insert into tbl_genre(name_genre) values ('фильм-нуар');
insert into tbl_genre(name_genre) values ('фэнтези');
insert into tbl_genre(name_genre) values ('эротика');
