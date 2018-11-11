-- DML 语言
/*
	数据操作语言：
		插入：INSERT
		修改：UPDATE
		删除：DELETE
*/

-- 一、插入语句
/*
	方式一：
		语法：
			INSERT INTO 表名(列名,...)
			VALUES(值1,...);
			
	方式二：
		语法：
			INSERT INTO 表名
			SET 列名 = 值,...;
			
	两种方式 pk：
		1. 方式一支持插入多行，方式二不支持
		2. 方式一支持子查询，方式二不支持
*/

SELECT * FROM beauty;

-- 1. 插入的值的类型要与列的类型一致或兼容
INSERT INTO beauty(id, beautyName, sex, borndate, phone, photo, boyfriend_id)
VALUES(13, '唐艺昕', '女', '1990-4-23', '18988888888', NULL, 2);

-- 2. 不可以为NULL的列必须插入值，可以为NULL的列如何插入值？
-- 方式一：插入NULL
INSERT INTO beauty(id, beautyName, sex, borndate, phone, photo, boyfriend_id)
VALUES(13, '唐艺昕', '女', '1990-4-23', '18988888888', NULL, 2);
-- 方式二：不写该列
INSERT INTO beauty(id, beautyName, sex, borndate, phone, boyfriend_id)
VALUES(14, '金星', '女', '1990-4-23', '18988888888', 9);

-- 3. 列的顺序是否可以调换
INSERT INTO beauty(beautyName, sex, id, phone)
VALUES('蒋欣', '女', 16, '110');

-- 4. 列数和值的个数必须一致
INSERT INTO beauty(beautyName, sex, id, phone)
VALUES('关晓彤', '女', 15, '110');

-- 5. 可以省略列名，默认所有列，而且列的顺序和表中列的顺序一致
INSERT INTO beauty
VALUES(18, '张飞', '男', NULL, 119, NULL, NULL);

-- 6. 插入子查询
INSERT INTO beauty(id, beautyName, phone)
SELECT 20, '宋茜', '11809866';


-- 方式二
INSERT INTO beauty
SET id = 19, beautyName = '刘涛', phone = '999';


-- 二、修改语句
/*
	1. 修改单表的记录
		语法：
			UPDATE 表名
			SET 列名 = 新值,...
			WHERE 筛选条件;
	2. 修改多表的记录[补充]
		语法：
			sql92：
				UPDATE 表1 别名, 表2 别名
				SET 列名= 新值,...
				WHERE 连接条件 AND 筛选条件;
			sql99：
				UPDATE 表1 别名
				INNER|LEFT|RIGHT JOIN 表2 别名 ON 连接条件
				SET 列 = 新值,...
				WHERE 筛选条件;
*/

-- 1. 修改单表的记录
-- 案例1：修改beauty表中姓唐的女生的电话为13899888899
UPDATE beauty
SET phone = '13899888899'
WHERE beautyName LIKE '唐%';

-- 案例2：修改boys表中id=2的名称为张飞，魅力值为10
UPDATE boys
SET boyName = '张飞', userCP = 10
WHERE id = 2;

-- 2. 修改多表的记录
-- 案例1：修改张无忌的女朋友的手机号为114
UPDATE boys b
INNER JOIN beauty g ON b.id = g.boyfriend_id
SET g.phone = 114
WHERE b.boyName = '张无忌';

-- 案例2：修改没有男朋友的女生的男朋友编号都为2
UPDATE boys b
RIGHT JOIN beauty g ON b.id = g.boyfriend_id
SET g.boyfriend_id = 2
WHERE b.id IS NULL;

-- 查询没有男朋友的女生的男朋友信息
SELECT * FROM boys b
RIGHT JOIN beauty g ON b.id = g.boyfriend_id
WHERE b.id IS NULL;


-- 三、删除语句
/*
	方式一：DELETE
		1. 单表的删除
			语法：
				DELETE FROM 表名
				WHERE 筛选条件;
		2. 多表的删除[补充]
			语法：
				sql92：
					DELETE 表1的别名, 表2的别名 FROM 表1 别名, 表2 别名
					WHERE 连接条件 AND 筛选条件;
				sql99：
					DELETE 表1的别名, 表2的别名 FROM 表1 别名
					INNER|LEFT|RIGHT JOIN 表2 别名 ON 连接条件
					WHERE 筛选条件;
		
	方式二：TRUNCATE
		语法：
			TRUNCATE TABLE 表名;
			
	DELETE, TRUNCATE pk
		1. DELETE 可以加WHERE条件，TRUNCATE不能加
		2. TRUNCATE 删除，效率高
		3. 加入要删除的表中有自增长列，如果用DELETE删除后，再插入数据，自增长的列的值从断点开始，
			而TRUNCATE删除后，再插入数据，自增长列的值从1开始
		4. DELETE 删除有返回值，TRUNCATE 删除无返回值
		5. DELETE 删除可以回滚，TRUNCATE 删除不能回滚
*/

-- 方式一：DELETE 语句

-- 1. 单表的删除
-- 案例1：删除手机号以9结尾的女生信息
DELETE FROM beauty
WHERE phone LIKE '%9';

-- 2. 多表的删除
-- 案例1：删除张无忌的女朋友的信息
DELETE g FROM beauty g
INNER JOIN boys b ON b.id = g.boyfriend_id
WHERE b.boyName = '张无忌';

-- 案例2：删除黄晓明的信息以及他女朋友的信息
DELETE g, b FROM beauty g
INNER JOIN boys b ON b.id = g.boyfriend_id
WHERE b.boyName = '黄晓明';

-- 方式二：TRUNCATE 语句
-- 案例1：将魅力值>100的男生删除
TRUNCATE TABLE boys;