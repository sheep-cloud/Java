-- DDL 语言
/*
	数据定义语言；库和表的管理
	
	分类：
		一、库的管理
			创建、修改、删除
		二、表的管理
			创建、修改、删除
			
	创建：CREATE
	修改：ALTER
	删除：DROP
*/

-- 一、库的管理
-- 1. 库的创建
/*
	语法：
		CREATE DATABASE 库名;
*/

-- 案例：创建库 books
CREATE DATABASE books;

-- 2. 库的修改（不安全，已过时；无法修改数据库名）
RENAME DATABASE books TO book;

-- 2.1. 修改库的字符集
ALTER DATABASE books CHARACTER SET utf8;

-- 3. 库的删除
DROP DATABASE IF EXISTS books;

-- 二、表的管理
-- 1. 表的创建
/*
	CREATE TABLE 表名 (
		列名 列的类型[(长度) 约束],
		列名 列的类型[(长度) 约束],
		列名 列的类型[(长度) 约束],
		...
		列名 列的类型[(长度) 约束]
	);
*/

-- 案例：创建表book
CREATE TABLE book (
    id INT,
    b_name VARCHAR(20),
    price DOUBLE,
    author INT,
    publish_date DATETIME
);

SHOW FULL COLUMNS FROM book;

-- 案例：创建表author
CREATE TABLE author (
    id INT,
    au_name VARCHAR(20),
    nation VARCHAR(10)
);

SHOW FULL COLUMNS FROM author;

-- 2. 表的修改
/*
	ALTER TABLE 表名 ADD|DROP|MODIFY|CHANGE COLUMN 列名 [新列名, 列类型, 约束];
*/
-- 2.1. 修改列名
ALTER TABLE book CHANGE COLUMN publish_date pub_date DATETIME;

-- 2.2. 修改列的类型或约束
ALTER TABLE book MODIFY COLUMN pub_date TIMESTAMP;

-- 2.3. 添加新列
-- ALTER TABLE 表名 ADD COLUMN 列名 [新列名, 列类型, 约束] [FIRST|AFTER 字段名];
ALTER TABLE author ADD COLUMN annal DOUBLE;

-- 2.4. 删除列
ALTER TABLE author DROP COLUMN annal;

-- 2.5. 修改表名
ALTER TABLE author RENAME TO book_author;

-- 3. 表的删除
DROP TABLE IF EXISTS book_author;


-- 通用的写法：
DROP DATABASE IF EXISTS 旧库名;
CREATE DATABASE 库名;

DROP TABLE IF EXISTS 旧表名;
CREATE TABLE 表名();

-- 4. 表的复制
INSERT INTO author(id, au_name, nation) VALUES
(1, '村上春树', '日本'),
(2, '莫言', '中国'),
(3, '冯唐', '中国'),
(4, '金庸', '中国');

-- 4.1. 复制表的结构
CREATE TABLE copy01_author LIKE author;

-- 4.2. 复制表的结构、数据
CREATE TABLE copy02_author
SELECT * FROM author;

-- 4.2. 复制表的结构、部分数据；复制部分数据（添加筛选条件）
CREATE TABLE copy03_author
SELECT * FROM author
WHERE nation = '中国';

-- 4.3. 复制表的部分结构
CREATE TABLE copy04_author
SELECT id, au_name FROM author
WHERE 0;