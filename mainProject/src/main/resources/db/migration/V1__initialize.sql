drop table if exists tbl_director cascade;
create table tbl_director (id_director bigserial, firstname_director varchar(255), lastname_director varchar(255),
description_director varchar(5000), primary key(id_director));
insert into tbl_director (firstname_director, lastname_director, description_director)  values ( 'Квентин', 'Тарантино',
'Актер, Сценарист, Режиссер, Продюсер, Оператор, Монтажер, рост  - 1.85 м, дата рождения - 27 марта, 1963
, всего фильмов	218, 1975 — 2022'),
( 'Люк', 'Бессон', 'Продюсер, Сценарист, Режиссер, Актер, Оператор, Монтажер, рост	1.72 м, дата рождения	18 марта, 1959, всего фильмов - 150, 1981 — 2019');
