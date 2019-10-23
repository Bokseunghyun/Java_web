create table bookTBL(
	code varchar2(4) primary key,
	title nvarchar2(50) not null,
	writer nvarchar2(10) not null,
	price number(8));
	
	
-- 검색
select * from BOOKTBL where code='1001';
-- %, _ : 포함된
select * from BOOKTBL where title like '%자바';
select * from BOOKTBL where title like '%자바%';
select * from BOOKTBL where title like '_자바_';

-- 자바에서 쿼리문 
select * from BOOKTBL where title like '%'||?||'%';




-- 문자열 연결함수(concat, ||)
select code, concat(title,writer) from bookTBL;
select code, concat(title,concat(' ',writer))
from bookTBL;

select code, title||' '||writer from bookTBL














