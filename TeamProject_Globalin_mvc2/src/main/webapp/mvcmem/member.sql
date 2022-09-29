select * from tab;


/* ���̺� ����� �ٲ���� */
create table member(
	id       varchar2(20) not null,
	nickname varchar2(10) not null,
	pass    varchar2(20) not null,
	name     varchar2(10) not null,
	phone1    varchar2(3) not null,
	phone2    varchar2(4) not null,
	phone3    varchar2(4) not null,
	email    varchar2(40) not null,
	constraint member_pk primary key(id)
);

drop table member;
update member set pass="wer",nickname="wer", phone1="02", phone2="1234", phone3="5678", email="dlqls12345@naver.com"  where id="wer";
select * from member;
