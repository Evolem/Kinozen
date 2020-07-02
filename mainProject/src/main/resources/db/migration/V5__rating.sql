create table if not exists public.tbl_content_like
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