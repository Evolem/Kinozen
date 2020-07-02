create table tbl_wish
(
    id_wish    uuid default uuid_generate_v4() not null
        constraint tbl_wishlist_pk
            primary key,
    id_user        uuid                            not null,
    id_content     uuid                            not null,
    added_wishlist date
);

insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('5e8ea056-929c-4694-aa76-d39b2706d3dc', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','1740acb5-a8c6-43f8-b8e1-faa74c92ea4a', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('5773615a-9301-4d08-b4b0-1ce34e9a92d2', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','8d0f77af-0679-4b53-a0ac-5f655c991ef0', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('a63b8642-42e7-4cd5-92a7-5f4754f3f029', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','68c2769d-2e9f-4dd7-b322-1d5124a05bef', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('73f7bcd6-e1c1-46f5-a644-67f54b170539', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','1c6b8365-7b92-4772-9406-458ca0e7f4ab', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('859994f5-f98a-466d-9a0c-2f1e40e77f47', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','47725759-864e-4d4d-a601-dd52d1506e2a', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('87a43b29-c6f4-4625-b63a-da03619daddc', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','8e3287b6-35f6-4a1d-81e8-47df3cf1f793', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('6a2d093c-7392-48fc-99cd-215f0c801f60', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','51bf778e-46e5-4f01-8c31-e6bb0de53a7c', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('ebf62a39-3f7e-4bcf-9e6f-0cd396dd35da', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','f3b18f94-67f5-43b8-a452-71b62f5e3230', '2020-06-01');
insert into tbl_wish (id_wish, id_user, id_content, added_wishlist)  VALUES('63b82dd5-52dd-4402-8dae-5e6069abb67e', 'ef39e14f-8e9e-4bd6-894f-3b7e99cf8089','86a38fc4-a9a6-45e8-a6c8-08aac7949f25', '2020-06-01');

