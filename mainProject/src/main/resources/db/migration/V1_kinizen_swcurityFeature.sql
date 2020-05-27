insert into tbl_role(id_role, name_role) values (1, 'ROLE_ADMIN');
insert into tbl_user(id_user, login_user, password_user, name_user) values (1, 'admin', '$2a$10$5rAOMKmVsh9.NlzXTLLbq.XwouGdg3dwohvb5/HDn692YfdrLthO2', 'vladimir');
insert into tbl_role_user (id_role, id_user) VALUES (1,1);