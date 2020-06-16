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



create table if not exists tbl_director
(
    id_director uuid default uuid_generate_v4() not null
        constraint tbl_director_pk
            primary key,
    firstname_director varchar(255),
    lastname_director varchar(255),
    description_director varchar(255),
    url_director varchar(255),
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
    type_content integer not null,
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
    url_actor varchar(255),
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
    id_entity uuid default uuid_generate_v4() not null,
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

-- Заполнение таблицы content
-- Сериалы
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('1740acb5-a8c6-43f8-b8e1-faa74c92ea4a','Игра престолов', 'тут описание', '2020-05-27', true, 0,'igra-prestolov');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('8d0f77af-0679-4b53-a0ac-5f655c991ef0','Пустыня смерти', 'тут описание', '2020-05-17', true, 0,'pustynya-smerti');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('68c2769d-2e9f-4dd7-b322-1d5124a05bef','Сопрано', 'тут описание', '2020-05-17', true, 0,'soprano');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('1c6b8365-7b92-4772-9406-458ca0e7f4ab','Рик и морти', 'тут описание', '2020-05-17', true, 0,'rik-i-morti');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('47725759-864e-4d4d-a601-dd52d1506e2a','Мир дикого запада', 'тут описание', '2020-05-17', true, 0,'mir-dikogo-zapada');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('8e3287b6-35f6-4a1d-81e8-47df3cf1f793','Убивая Еву', 'тут описание', '2020-05-17', true, 0,'ubivaya-yevu');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('51bf778e-46e5-4f01-8c31-e6bb0de53a7c','Шепот', 'тут описание', '2020-05-17', true, 0,'shepot');
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('f3b18f94-67f5-43b8-a452-71b62f5e3230','Южный парк', 'тут описание', '2020-05-17', true, 0,'yuzhnyy-park');
--Фильмы
insert into tbl_content(id_content ,name_content, description_content, released_content, visible_content, type_content, url_content) values ('86a38fc4-a9a6-45e8-a6c8-08aac7949f25','Интерстеллар', 'тут описание', '2020-05-27', true, 1,'interstellar');


-- Заполенение Жанров
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('295c4a70-f4ca-4e49-8cd7-3542c53f4925','аниме','anime');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('57ebe16d-f03c-4042-8383-af34d6eb5797','биографический','biograficheskiy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('2873a8ec-cbdd-4d3c-947f-f78d598862fc','боевик','boyevik');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('5f90ff46-8310-498f-824c-cc4acdb1d056','вестерн','vestern');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('a06e6e92-4eec-480d-8f21-3e4bed9689da','военный','voyennyy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('befbffe3-ac6b-4674-825e-6daff22b4445','детектив','detektiv');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('17cbcddf-3c26-48a0-9f00-a70642339c00','детский','detskiy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('63964689-8f8c-4f11-9817-32a6c9dc1c6a','документальный','dokumentalnyy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('a9e52f5e-f93c-4d08-864a-ad98d291a7c8','драма','drama');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('d5a2b664-4065-4d16-aff4-5fcac7fd132a','исторический','istoricheskiy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('545f2219-c2b9-4091-9aec-1f52396bf2b0','кинокомикс','kinokomiks');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('cb682ebe-bebf-4bcf-9533-584f746086f0','комедия','komediya');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('522d65dd-9415-409b-b80f-849d937e20b6','концерт','kontsert');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('97af4963-8e4b-4472-ad2c-f2bdf894663b','короткометражный','korotkometrazhnyy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('983cb528-6018-4ae0-a1a8-d19d17e93d36','криминал','kriminal');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('b52879d8-6e13-45f6-8414-3e7b31e635d2','мелодрама','melodrama');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('9ff50344-2240-45a3-9cbc-5f54fddefee4','мистика','mistika');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('50cd1c82-ac85-4af6-b255-5a025f0fa101','музыка','muzyka');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('9f73d230-bd23-444c-a9c8-51cfe7efa96f','мультфильм','multfilm');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('83bad475-9608-4076-ab75-0dba46f7191b','мюзикл','myuzikl');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('d87bcaa3-8845-4e99-b5f7-ead063826737','научный','nauchnyy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('af140e6a-0c8b-4f53-859f-6a028c4a94bd','приключения','priklyucheniya');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('d1440361-4090-49da-8e05-ae44d35dc049','реалити-шоу','realiti-shou');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('3a7deea6-aeab-496c-82be-58349c03998e','семейный','semeynyy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('052cc798-4aa9-4e3a-8412-44513f47c114','спорт','sport');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('3e3fc3bd-7d85-494d-8322-77e0f817a57d','ток-шоу','tok-shou');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('9bb0f262-d837-4734-b269-d83733de435a','триллер','triller');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('bbe924ec-4d60-40eb-b2ba-c02bcfacab99','ужасы','uzhasy');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('d1427e89-5737-4c34-bcef-a4848101179b','фантастика','fantastika');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('2a27990a-61ce-4898-b522-80445d6d59ff','фильм-нуар','film-nuar');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('249a3e23-b911-4dc3-ad1a-b7bbc0be3cd7','фэнтези','fentezi');
insert into tbl_genre(id_genre ,name_genre, url_genre) values ('411cc4ce-3c78-41da-81d4-4aa9135514d6','эротика','erotika');

-- Связка жанров и контента
insert into tbl_genre_content(id_genre, id_content) values ('295c4a70-f4ca-4e49-8cd7-3542c53f4925','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_genre_content(id_genre, id_content) values ('57ebe16d-f03c-4042-8383-af34d6eb5797','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_genre_content(id_genre, id_content) values ('2873a8ec-cbdd-4d3c-947f-f78d598862fc','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_genre_content(id_genre, id_content) values ('5f90ff46-8310-498f-824c-cc4acdb1d056','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_genre_content(id_genre, id_content) values ('a06e6e92-4eec-480d-8f21-3e4bed9689da','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_genre_content(id_genre, id_content) values ('befbffe3-ac6b-4674-825e-6daff22b4445','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_genre_content(id_genre, id_content) values ('17cbcddf-3c26-48a0-9f00-a70642339c00','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_genre_content(id_genre, id_content) values ('63964689-8f8c-4f11-9817-32a6c9dc1c6a','f3b18f94-67f5-43b8-a452-71b62f5e3230');

insert into tbl_genre_content(id_genre, id_content) values ('a9e52f5e-f93c-4d08-864a-ad98d291a7c8','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_genre_content(id_genre, id_content) values ('d5a2b664-4065-4d16-aff4-5fcac7fd132a','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_genre_content(id_genre, id_content) values ('545f2219-c2b9-4091-9aec-1f52396bf2b0','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_genre_content(id_genre, id_content) values ('cb682ebe-bebf-4bcf-9533-584f746086f0','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_genre_content(id_genre, id_content) values ('522d65dd-9415-409b-b80f-849d937e20b6','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_genre_content(id_genre, id_content) values ('97af4963-8e4b-4472-ad2c-f2bdf894663b','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_genre_content(id_genre, id_content) values ('983cb528-6018-4ae0-a1a8-d19d17e93d36','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_genre_content(id_genre, id_content) values ('b52879d8-6e13-45f6-8414-3e7b31e635d2','f3b18f94-67f5-43b8-a452-71b62f5e3230');

insert into tbl_genre_content(id_genre, id_content) values ('295c4a70-f4ca-4e49-8cd7-3542c53f4925','f3b18f94-67f5-43b8-a452-71b62f5e3230');
insert into tbl_genre_content(id_genre, id_content) values ('57ebe16d-f03c-4042-8383-af34d6eb5797','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_genre_content(id_genre, id_content) values ('2873a8ec-cbdd-4d3c-947f-f78d598862fc','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_genre_content(id_genre, id_content) values ('5f90ff46-8310-498f-824c-cc4acdb1d056','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_genre_content(id_genre, id_content) values ('a06e6e92-4eec-480d-8f21-3e4bed9689da','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_genre_content(id_genre, id_content) values ('befbffe3-ac6b-4674-825e-6daff22b4445','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_genre_content(id_genre, id_content) values ('17cbcddf-3c26-48a0-9f00-a70642339c00','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_genre_content(id_genre, id_content) values ('63964689-8f8c-4f11-9817-32a6c9dc1c6a','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');

insert into tbl_genre_content(id_genre, id_content) values ('a9e52f5e-f93c-4d08-864a-ad98d291a7c8','f3b18f94-67f5-43b8-a452-71b62f5e3230');
insert into tbl_genre_content(id_genre, id_content) values ('d5a2b664-4065-4d16-aff4-5fcac7fd132a','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_genre_content(id_genre, id_content) values ('545f2219-c2b9-4091-9aec-1f52396bf2b0','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_genre_content(id_genre, id_content) values ('cb682ebe-bebf-4bcf-9533-584f746086f0','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_genre_content(id_genre, id_content) values ('522d65dd-9415-409b-b80f-849d937e20b6','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_genre_content(id_genre, id_content) values ('97af4963-8e4b-4472-ad2c-f2bdf894663b','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_genre_content(id_genre, id_content) values ('983cb528-6018-4ae0-a1a8-d19d17e93d36','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_genre_content(id_genre, id_content) values ('b52879d8-6e13-45f6-8414-3e7b31e635d2','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');

-- Заполнение актеров
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('b2154a81-f5d7-4c63-963b-7580065fccd9', 'Василий', 'Медведев', 'Очень хороший человек и актеров','vasiliy-medvedev');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('4590fe12-ad94-4b07-a474-0b63daad377b', 'Григорич', 'Путин', 'Очень хороший человек и актеров','grigorich-putin');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('3c59fb86-baa8-4f47-9cd1-4a8b28f35ba4', 'Сергей', 'Рогозин', 'Очень хороший человек и актеров','sergey-rogozin');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('925d3d6d-712e-4248-8886-ae53dcffed93', 'Андрей', 'Собянин', 'Очень хороший человек и актеров','andrey-sobyanin');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('f08fc5cb-bba6-4d2e-baaf-35f431b95ef9', 'Кира', 'Лавров', 'Очень хороший человек и актеров','kira-lavrov');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('96c623da-7691-4f91-80a4-fbe8a7e53e11', 'Сема', 'Топвый', 'Очень хороший человек и актеров','sema-topvyy');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('452c769d-3b26-4ddc-82dd-84715e629c99', 'Евген', 'Нолан', 'Очень хороший человек и актеров','yevgen-nolan');
insert into tbl_actor (id_actor, firstname_actor, lastname_actor, description_actor, url_actor) VALUES ('8da0729b-7bcc-4e00-b83f-9d25be64b6d1', 'Маша', 'Симосян', 'Очень хороший человек и актеров','masha-simosyan');

-- Связка актеров и контента
insert into tbl_actor_content(id_actor, id_content) VALUES ('b2154a81-f5d7-4c63-963b-7580065fccd9','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_actor_content(id_actor, id_content) VALUES ('4590fe12-ad94-4b07-a474-0b63daad377b','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_actor_content(id_actor, id_content) VALUES ('3c59fb86-baa8-4f47-9cd1-4a8b28f35ba4','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_actor_content(id_actor, id_content) VALUES ('925d3d6d-712e-4248-8886-ae53dcffed93','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_actor_content(id_actor, id_content) VALUES ('f08fc5cb-bba6-4d2e-baaf-35f431b95ef9','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_actor_content(id_actor, id_content) VALUES ('96c623da-7691-4f91-80a4-fbe8a7e53e11','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_actor_content(id_actor, id_content) VALUES ('452c769d-3b26-4ddc-82dd-84715e629c99','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_actor_content(id_actor, id_content) VALUES ('8da0729b-7bcc-4e00-b83f-9d25be64b6d1','f3b18f94-67f5-43b8-a452-71b62f5e3230');

insert into tbl_actor_content(id_actor, id_content) VALUES ('8da0729b-7bcc-4e00-b83f-9d25be64b6d1','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_actor_content(id_actor, id_content) VALUES ('452c769d-3b26-4ddc-82dd-84715e629c99','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_actor_content(id_actor, id_content) VALUES ('96c623da-7691-4f91-80a4-fbe8a7e53e11','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_actor_content(id_actor, id_content) VALUES ('f08fc5cb-bba6-4d2e-baaf-35f431b95ef9','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_actor_content(id_actor, id_content) VALUES ('925d3d6d-712e-4248-8886-ae53dcffed93','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_actor_content(id_actor, id_content) VALUES ('3c59fb86-baa8-4f47-9cd1-4a8b28f35ba4','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_actor_content(id_actor, id_content) VALUES ('4590fe12-ad94-4b07-a474-0b63daad377b','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_actor_content(id_actor, id_content) VALUES ('b2154a81-f5d7-4c63-963b-7580065fccd9','f3b18f94-67f5-43b8-a452-71b62f5e3230');

-- заполнение режжисера
insert into tbl_director(id_director, firstname_director, lastname_director, description_director, url_director)
values ('cc959d7b-ba6a-4458-be47-6e8cca63bafb', 'Алексей', 'Петров', 'Андройд и учитель Нолана, собрал все в мире нагрды и снял лучшие фильмы', 'alex-petrov');

-- связка режиисера и контента
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','68c2769d-2e9f-4dd7-b322-1d5124a05bef');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','47725759-864e-4d4d-a601-dd52d1506e2a');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','51bf778e-46e5-4f01-8c31-e6bb0de53a7c');
insert into tbl_director_content(id_director, id_content) VALUES ('cc959d7b-ba6a-4458-be47-6e8cca63bafb','f3b18f94-67f5-43b8-a452-71b62f5e3230');

-- заполнение сезонов (игра престолов)
insert into tbl_season (id_season, id_content, number_season, description_season) values ('57d12cd4-ec35-42c1-9b88-22ea330c2b10','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a', 1 , 'Откровенно фиговый сезон');
insert into tbl_season (id_season, id_content, number_season, description_season) values ('1b71c4be-5361-4e2c-94e9-93caf73eecaf','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a', 2 , 'Та же шляпа');
insert into tbl_season (id_season, id_content, number_season, description_season) values ('5e291fb1-6962-4a06-9d82-970d46b3833c','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a', 3 , 'Этот сезон точно интереснее предыдущих');

-- заполнение эпизодов по сезонам (игра престолов)
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('742da102-b403-4934-9f3c-b74a5ce07860','57d12cd4-ec35-42c1-9b88-22ea330c2b10', 1, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('0ca26d11-f2b6-4955-a617-d9bd390b4713','57d12cd4-ec35-42c1-9b88-22ea330c2b10', 2, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('83580aa6-01f2-4d31-8f5d-ece408ce334f','57d12cd4-ec35-42c1-9b88-22ea330c2b10', 3, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('473cb191-5152-468a-adf0-3f690c087ea9','1b71c4be-5361-4e2c-94e9-93caf73eecaf', 1, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('ced70d3c-1d08-4456-84e4-20e3d3500594','1b71c4be-5361-4e2c-94e9-93caf73eecaf', 2, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('d9da9647-38c6-430b-b8ab-b366a08cffdb','1b71c4be-5361-4e2c-94e9-93caf73eecaf', 3, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('68cc52a9-c495-4912-881a-d021f7d8bf0e','5e291fb1-6962-4a06-9d82-970d46b3833c', 1, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('d2ccf26a-bdac-4251-90a1-a45cf90ff297','5e291fb1-6962-4a06-9d82-970d46b3833c', 2, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
insert into tbl_episode(id_episode, id_season, number_episode, name_episode, description_episode) values ('e719e33e-7bcb-49b4-a9f7-a8ce09463b6b','5e291fb1-6962-4a06-9d82-970d46b3833c', 3, 'Lorem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');


-- добавление комментариев к фильму
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');
insert into tbl_comment (id_user, id_entity, text_comment, date_comment) values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', '2020-05-27');



