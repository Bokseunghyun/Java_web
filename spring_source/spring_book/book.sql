create table book(code varchar2(4) primary key,title nvarchar2(50) not null,writer nvarchar2(10) not null,price number(8) not null);
insert into book values('aa','bb','cc','11');