create table member(
userid varchar2(15) primary key,
password varchar2(20) not null,
name nvarchar2(10) not null,
gender varchar2(10) not null,
email varchar2(50) not null);

insert into member values('hong123','hong123!5','홍길동','남','hong123@gmail.com');

