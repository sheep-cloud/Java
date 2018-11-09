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
CREATE DATABASE IF NOT EXISTS books;