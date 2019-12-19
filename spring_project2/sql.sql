create table spring_member(
userid varchar2(15) primary key,
userpw varchar2(100) not null,
username nvarchar2(10) not null,
gender varchar2(10) not null,
email varchar2(50) not null);

select * from SPRING_MEMBER;

drop table spring_member;

select * from SPRING_BOARD;

create table spring_board(
bno int primary key,
title varchar2(100) not null,
writer varchar2(100) not null,
content varchar2(100) not null,
regdate timestamp default sysdate,
updatedate timestamp default sysdate,
replyCnt number default 0 
)

--댓글 테이블 생성
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, --댓글 글번호
	bno number(10,0) not null, -- spring_board bno
	reply varchar2(1000) not null, -- 댓글 내용
	replyer varchar2(50) not null, -- 댓글 작성자
	replyDate date default sysdate, -- 댓글 작성일
	updateDate date default sysdate, -- 댓글 수정일
	constraint fk_reply_board foreign key(bno)
	references spring_board(bno) on delete cascade
)

-- 파일 첨부 테이블
create table spring_attach(
	uuid varchar2(100) not null ,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) default 'I',
	bno number(10,0),
	constraint pk_attach primary key(uuid),
	constraint fk_board_attach foreign key(bno)
	references spring_board(bno) on delete cascade
);
drop table spring_reply;

create sequence seq_reply;
select * from spring_reply;
--인덱스 생성
create index idx_reply on spring_reply(bno desc,rno asc);

--댓글 수 처리를 위한 컬럼 추가
alter table spring_board add(replyCnt number default 0);

update SPRING_BOARD set replyCnt=(select count(rno) from SPRING_REPLY where SPRING_REPLY.bno=spring_board.bno);

alter table spring_board rename column reqdate to regdate;
alter table spring_board modify(regdate timestamp default sysdate);
alter table spring_board modify(updatedate timestamp default sysdate);
drop table spring_attach;
drop table spring_reply;
drop table spring_board;

alter table spring_reply add constraint fk_reply_board foreign key(bno)
references spring_board(bno) on delete cascade


select * from spring_board;
CREATE SEQUENCE seq_board;