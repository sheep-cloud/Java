-- TCL：事务控制语言
/*
	Transaction Control Language 事务控制语言
	
	事务：
		一个或一组sql语句组成一个执行单元，这个执行单元要么全部执行，要么全部不执行。
		
	案例：转账
		 张三丰	1000
		 郭襄	1000
		 
		 UPDATE 表 SET 张三丰的余额 = 500 WHERE name = '张三丰';
		 意外
		 UPDATE 表 SET 郭襄的余额 = 1500 WHERE name = '郭襄';
		 
	事务的特性（ACID属性）：
		1. 原子性（Atomicity）
			原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
 		2. 一致性（Consistecy）
			事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
		3. 隔离性（Isolation）
			事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
		4. 持久性（Durability）
			持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响。
			
	事务的创建
		隐式事务：事务没有明显的开启和结束的标记；比如：INSERT, UPDATE, DELETE语句
		显式事务：事务具有明显的开启和结束的标记；前提：必须设置自动提交功能为禁用 SET autocommit = 0;
			步骤1：开启事务
				SET autocommit = 0;
				BEGIN;或START TRANSACTION;
			步骤2：编写事务中的sql语句（SELECT, INSERT, UPDATE, DELETE）
				语句1;
				语句2;
				...
			步骤3：结束事务
				COMMIT; 提交事务
				ROLLBACK; 回滚事务
				
	MYSQL 事务处理主要有两种方法：
		1. 用 BEGIN, ROLLBACK, COMMIT来实现
		2. 直接用 SET 来改变 mysql 的自动提交模式：
			SET AUTOCOMMIT=0 禁止自动提交
			SET AUTOCOMMIT=1 开启自动提交
			
	并发事务：
		1. 事务的并发问题是如何发生的？
			多个事务同事操作同一个数据库的相同数据时
		2. 并发问题都有哪些？
			2.1. 脏读：		一个事务读取了其他事务还没提交的数据，读到的是其他事务"更新"的数据
			2.2. 不可重复读：	一个事务多次读取，结果不一样
			2.3. 幻读：		一个事务读取了其他事务还没提交的数据，读到的是其他事务"插入"的数据
		3. 如何解决并发问题？
			通过设置隔离级别来解决并发问题
		4. 隔离级别有哪些？解决了哪些并发问题？
								脏读	不可重复读	幻读
			1. READ UNCOMMITTED	读未提交	×	×		×
			2. READ COMMITTED	读已提交	√	×		×	ORACLE 默认
			3. REPEATABLE READ	可重复读	√	√		×	MYSQL 默认
			4. SERIALIZABLE		串行化		√	√		√
*/

-- 查看数据库存储引擎
SHOW ENGINES;

SHOW VARIABLES LIKE 'autocommit';
SET autocommit = 1;

-- 演示事务的使用步骤
USE test;
DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20),
	balance DOUBLE
);

INSERT INTO account(username, balance) VALUES
('张无忌', 1000),
('赵敏', 1000);

-- 1. 开启事务
BEGIN;
-- 2. 编写一组事务的语句
UPDATE account SET balance = 500 WHERE username = '张无忌';
UPDATE account SET balance = 1500 WHERE username = '赵敏';
-- 3. 结束事务
COMMIT; -- 提交事务
ROLLBACK; -- 回滚事务

SELECT * FROM account;