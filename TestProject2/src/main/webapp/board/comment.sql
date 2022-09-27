create table comment(
boardID int,
commentID int,
bbsID int,
userID varchar(20),
commentDate varchar(50),
commentText varchar(100),
commentAvailable int
);

alter table comment add primary key (commentID, bbsID);
alter table comment add constraint bbsID foreign key bbsID references bbs(bbsID);

--위에게 원본--

create table commentDB(
commentID number(7),
num number(7),
userID varchar2(20),
userName varchar2(30),
commentDate varchar2(50),
commentText varchar2(4000),
commentAvailable number(7)
);

alter table commentDB add primary key (commentID, num);
alter table commentDB add constraint commentID_fk foreign key(num) references board(num) on delete cascade;

select * from commentDB;

DELETE FROM commentDB
           WHERE commentDate = NULL;

INSERT INTO commentDB 
VALUES 
(
1,
65, 
'test', 
'2022-10-08', 
'asd',
1
);

SELECT SYSDATE FROM DUAL;

DROP TABLE commentDB CASCADE CONSTRAINTS;

alter table commentDB modify (commentText varchar2(4000)) ;