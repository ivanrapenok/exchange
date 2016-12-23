create table SHARES_TABLE
(
  share_id   VARCHAR2(100) not null,
  share_type VARCHAR2(100),
  price      NUMBER(20,4)
);

alter table SHARES_TABLE
  add primary key (SHARE_ID);
alter table SHARES_TABLE
  add constraint PRICE_NOT_NULL
  check ("PRICE" IS NOT NULL);

create table USERS_TABLE
(
  user_id   VARCHAR2(100) not null,
  user_pswd VARCHAR2(100),
  group_id  VARCHAR2(100),
  money     NUMBER(20,4)
);

alter table USERS_TABLE
  add primary key (USER_ID);
alter table USERS_TABLE
  add constraint MONEY_NOT_NULL
  check ("MONEY" IS NOT NULL);

create table OWNERSHIPS
(
  user_owner_id VARCHAR2(100) not null,
  share_own_id  VARCHAR2(100) not null,
  share_count   NUMBER(10)
);

alter table OWNERSHIPS
  add primary key (USER_OWNER_ID, SHARE_OWN_ID);
  
alter table OWNERSHIPS
  add constraint OWNERFK foreign key (USER_OWNER_ID)
  references USERS_TABLE (USER_ID);
alter table OWNERSHIPS
  add constraint OWNSHAREFK foreign key (SHARE_OWN_ID)
  references SHARES_TABLE (SHARE_ID);

create table TRADES
(
  trade_id          NUMBER(10) default "EWA"."TRADES_SEQ"."NEXTVAL" not null,
  trade_type        NUMBER(2),
  share_share_id    VARCHAR2(100) not null,
  share_count       NUMBER(10) not null,
  user_initiator_id VARCHAR2(100) not null,
  user_client_id    VARCHAR2(100),
  price             NUMBER(20,4) not null
);

alter table TRADES
  add primary key (TRADE_ID);
alter table TRADES
  add constraint CLIENTFK foreign key (USER_CLIENT_ID)
  references USERS_TABLE (USER_ID);
alter table TRADES
  add constraint INITIATORFK foreign key (USER_INITIATOR_ID)
  references USERS_TABLE (USER_ID);
alter table TRADES
  add constraint SHAREFK foreign key (SHARE_SHARE_ID)
  references SHARES_TABLE (SHARE_ID);
alter table TRADES
  add check (trade_type in (0, 1));