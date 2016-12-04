create table USERS_TABLE
(
  user_id   VARCHAR2(100) not null,
  user_pswd VARCHAR2(100),
  group_id  VARCHAR2(100),
  money     NUMBER(20,4)
)

alter table USERS_TABLE
  add primary key (USER_ID)
);

insert into USERS_TABLE (user_id, user_pswd, group_id, money)
values ('johny', '1111', 'user', 1000);
commit;
