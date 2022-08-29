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
commentDate varchar2(50),
commentText varchar2(100),
commentAvailable number(7)
);

alter table commentDB add primary key (commentID, num);
alter table commentDB add constraint bbsID foreign key(num) references board(num);

select * from commentDB;

DELETE FROM commentDB
           WHERE userID = 'test';
           
INSERT INTO commentDB 
VALUES 
(
1,
65, 
test, 
2022-10-08, 
asd,
1
);