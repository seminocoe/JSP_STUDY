select * from tab;

create table board(
num			number(7) not null,
writer		varchar2(15) not null,
email		varchar2(30) not null,
subject		varchar2(50) not null,
pass			varchar2(10)	 not null,
readcount	number(5) default 0 not null,
ref			number(5) default 0 not null,
step			number(3) default 0 not null,
depth		number(3) default 0 not null,
regdate		timestamp(6) default sysdate not null,
content 	varchar2(4000) not null,
ip				varchar2(20) not null,
constraint board_pk primary key(num)
);

create sequence board_seq
start with 1
increment by 1
nomaxvalue
nocache
nocycle;

select * from board;

alter table board add imageUID varchar2(50) default '';