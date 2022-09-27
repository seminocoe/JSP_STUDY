create table evaluation(
num int,
userID VARCHAR2(20),
gechu int,
bichu int
);

alter table evaluation add primary key (num, userID);
alter table evaluation add constraint evluation_fk foreign key (num) references board(num) on delete cascade;

select * from EVALUATION;
DESC EVALUATION;--이건 디벨로퍼에서만 먹힘

insert into evaluation(
num,
userID,
gechu,
bichu
)
values(
23,
'test',
1,
0
);

ALTER TABLE evaluation DROP CONSTRAINT evluation_fk;
alter table evaluation add constraint evluation_fk foreign key (num) references board(num) on delete cascade;
