alter table if exists tbl_episode
    add column if not exists added_episode date;

update  tbl_episode e SET added_episode = '2020-06-29' where e.id_episode = '742da102-b403-4934-9f3c-b74a5ce07860';
update  tbl_episode e SET added_episode = '2020-06-30' where e.id_episode = '0ca26d11-f2b6-4955-a617-d9bd390b4713';
update  tbl_episode e SET added_episode = '2020-07-1'  where e.id_episode = '83580aa6-01f2-4d31-8f5d-ece408ce334f';
update  tbl_episode e SET added_episode = '2020-07-2'  where e.id_episode = '473cb191-5152-468a-adf0-3f690c087ea9';
update  tbl_episode e SET added_episode = '2020-07-3'  where e.id_episode = 'ced70d3c-1d08-4456-84e4-20e3d3500594';
update  tbl_episode e SET added_episode = '2020-07-4'  where e.id_episode = 'd9da9647-38c6-430b-b8ab-b366a08cffdb';
update  tbl_episode e SET added_episode = '2020-07-5'  where e.id_episode = '68cc52a9-c495-4912-881a-d021f7d8bf0e';
update  tbl_episode e SET added_episode = '2020-07-6'  where e.id_episode = 'd2ccf26a-bdac-4251-90a1-a45cf90ff297';
update  tbl_episode e SET added_episode = '2020-07-7'  where e.id_episode = 'e719e33e-7bcb-49b4-a9f7-a8ce09463b6b';


