--tempmember.sql
select * from tab;

create table tempmember(

id varchar2(20) not null,
passwd varchar2(20),
name varchar2(20),
mem_num1 varchar2(6),
mem_num2 varchar2(7),
e_mail varchar2(50),
phone varchar2(30),
zipcode varchar2(7),
address varchar2(80),
job varchar2(80),
primary key (id)

)

select * from TEMPMEMBER 

insert into TEMPMEMBER values ('aaaa', '1111', '홍길동', '123456', '7654321', 'hong@naver.com', '010-1234-1234', '100-100', '서울특별시 영등포구 영종로 56 신한빌딩 4층', '프로그래머');
insert into TEMPMEMBER values ('bbbb', '2222', '손오공', '654321', '7654321', 'son@naver.com', '010-1234-4321', '200-200', '부산특별시 영등포구 영종로 56 신한빌딩 4층', '격투가');
insert into TEMPMEMBER values ('cccc', '3333', '강감찬', '654321', '1234567', 'kang@naver.com', '010-4321-1234', '300-300', '함경북도시 영등포구 영종로 56 신한빌딩 4층', '고려장군');
