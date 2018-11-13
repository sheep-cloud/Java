/*
	1、A(id ,name,regdate) B(id,groupid) C(id,name2) 写出下面的SQL语句 
	  1. 统计A表中每个月注册用户数 
	  2. 统计A表中有姓名相同的用户数 
	  3. 如果表A中有姓名相同的用户,把相同的查出,写入表C中 
	  4. A中ID有多个相同的数据,A中姓名相同的ID只保留注册时间最大的数据
*/

CREATE TABLE A (
	id INT,
	`name` VARCHAR(32),
	regdate DATETIME
);

SELECT * FROM A;
INSERT INTO A(id, `name`, regdate) VALUES
(1, 'jack', '2014-11-12 15:06:52'),
(1, 'jack1', '2014-11-12 15:06:52'),
(2, 'jack', '2004-11-12 15:06:52'),
(3, 'jackc', '2014-10-02 15:06:52'),
(4, 'jacka', '2018-11-12 15:06:52'),
(3, 'jack', '2017-11-12 15:06:52');

-- 1. 统计A表中每个月注册用户数
SELECT
    DATE_FORMAT(regdate, '%Y年%m月') 注册日期, COUNT(*)
FROM
    a
GROUP BY 注册日期;

-- 2. 统计A表中有姓名相同的用户数 
SELECT
    `name`, COUNT(*)
FROM
    A
GROUP BY `name`
HAVING COUNT(*) > 1;

-- 3. 如果表A中有姓名相同的用户,把相同的查出,写入表C中
CREATE TABLE C
SELECT
    *
FROM
    A
WHERE `name` IN (
    SELECT
        `name`
    FROM
        A
    GROUP BY `name`
    HAVING COUNT(*) > 1
);

SELECT * FROM C;

-- 4. 表A中姓名相同的ID只保留注册时间最大的数据
SELECT
    *
FROM
    A
GROUP BY `name`
HAVING MIN(regdate);