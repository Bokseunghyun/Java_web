select count(*)from SPRING_BOARD;

-- 더미 데이터

insert into spring_board(bno,title,content,writer) (select seq_board.nextVal,title,content,writer from spring_board);

-- rownum : 가상 컬럼(임시 부여)
select rownum, bno, title from spring_board where rownum<=10;


-- rownum 과 order by는 같이 쓸 때 주의(rownum의 우선순위가 높음)


-- spring_board에서 최신글 1~10개 가져오기
select bno,title,writer from (select /*INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title,writer from spring_board where rownum<=10) where rn>0;

-- spring_board에서 최신글 11~20개 가져오기
select bno,title,writer from (select /*INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title,writer from spring_board where rownum<=20) where rn>10;


-- ? : 1page = 1 ~ 10 1page*10개씩 보기 / (1page-1)*10
-- ? : 2page = 11 ~ 20 2page*10개씩 보기 / (2page-1)*10

select bno,title,writer from (select /*INDEX_DESC(spring_board pk_spring_board)*/ rownum rn,bno,title,writer from spring_board where rownum<=?) where rn>?;


--댓글 테이블 생성
create table spring_reply(
	rno number(10,0) constraint pk_reply primary key, --댓글 글번호
	bno number(10,0) not null, -- spring_board bno
	reply varchar2(1000) not null, -- 댓글 내용
	replyer varchar2(50) not null, -- 댓글 작성자
	replyDate date default sysdate, -- 댓글 작성일
	updateDate date default sysdate, -- 댓글 수정일
	constraint fk_reply_board foreign key(bno)
	references spring_board(bno)
)


create sequence seq_reply;
select * from spring_reply;

--인덱스 생성
create index idx_reply on spring_reply(bno desc,rno asc);

--댓글 수 처리를 위한 컬럼 추가
alter table spring_board add(replyCnt number default 0);

update SPRING_BOARD set replyCnt=(select count(rno) from SPRING_REPLY where SPRING_REPLY.bno=spring_board.bno);

select * from SPRING_BOARD;


-- 파일 첨부 테이블
create table spring_attach(
	uuid varchar2(100) not null,
	uploadPath varchar2(200) not null,
	fileName varchar2(100) not null,
	fileType char(1) default 'I',
	bno number(10,0)
);

alter table spring_attach add constraint pk_attach primary key(uuid);
alter table spring_attach add constraint fk_board_attach foreign key(bno)
references spring_board(bno);



-- BoardAttachVO