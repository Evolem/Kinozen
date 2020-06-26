create table if not exists public.tbl_content_dislike
(
    id_user uuid NOT NULL,
    id_content uuid NOT NULL,
    PRIMARY KEY (id_user, id_content),
    CONSTRAINT id_user_fk FOREIGN KEY (id_user)
        REFERENCES public.tbl_user (id_user) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT id_content_fk FOREIGN KEY (id_content)
        REFERENCES public.tbl_content (id_content) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

-- admin ставит лайки на контент
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', 'f3b18f94-67f5-43b8-a452-71b62f5e3230');
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '8e3287b6-35f6-4a1d-81e8-47df3cf1f793');
insert into tbl_content_like (id_user, id_content)
values ('ef39e14f-8e9e-4bd6-894f-3b7e99cf8089', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25');

-- user1 ставит лайки на контент
insert into tbl_content_like (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', '1740acb5-a8c6-43f8-b8e1-faa74c92ea4a');
insert into tbl_content_like (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', '1c6b8365-7b92-4772-9406-458ca0e7f4ab');
insert into tbl_content_like (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', '8d0f77af-0679-4b53-a0ac-5f655c991ef0');
insert into tbl_content_like (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', 'f3b18f94-67f5-43b8-a452-71b62f5e3230');
insert into tbl_content_like (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', '8e3287b6-35f6-4a1d-81e8-47df3cf1f793');

-- user1 ставит дизлайк интерстеллару
insert into tbl_content_dislike (id_user, id_content)
values ('b774430e-46a4-4801-8eb1-49b4022c745c', '86a38fc4-a9a6-45e8-a6c8-08aac7949f25');