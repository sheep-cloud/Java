-- 流程控制结构
/*
	顺序结构：程序从上往下依次执行
	分支结构：程序从两条或多条路径中选择一条去执行
	循环结构：程序在满足一定条件的基础上，重复执行一段代码
*/

-- 一、分支结构
-- 1. IF函数
/*
	功能：实现简单的双分支
	语法：
		SELECT IF(表达式1, 表达式2, 表达式3)
	执行顺序：
		如果表达式1成立，则IF函数返回表达式2的值，否则返回表达式3的值
		
	应用：任何地方
*/

-- 2. CASE结构
/*
	情况1：类似于java中的switch语句，一般用于实现的等值判断
		CASE 变量|表达式|字段
			WHEN 要判断的值1 THEN 返回的值1或语句1
			WHEN 要判断的值 THEN 返回的值2或语句2
			...
			ELSE 要返回的值n或语句n;
		END CASE;
	情况2：类似于java中的多重IF语句，一般用于实现的区间判断
		CASE
			WHEN 要判断的条件1 THEN 返回的值1或语句1;
			WHEN 要判断的条件2 THEN 返回的值2或语句2;
			...
			ELSE 要返回的值n或语句n;
		END CASE;
		
	特点：
		1. 可以作为表达式，嵌套在其他语句中使用，可以放在任何地方，BEGIN END 中或 BEGIN END 的外面
		2. 可以作为独立的语句去使用，只能放在BEGIN END中
		3. 如果WHEN中的值或条件成立，则执行对应的THEN后面的语句，并且结束CASE，如果都不满足，则执行ELSE中的语句或值
		4. ELSE可以省略，如果ELSE省略了，并且所有WHEN条件都不满足，返回NULL
		
*/

-- 案例
-- 创建存储过程，根据传入的成绩，来显示登记，比如传入的成绩：90-100，显示A；80-90，显示B；60-80，显示C；否则，显示D
DELIMITER $
CREATE PROCEDURE test_case(IN score DOUBLE)
BEGIN
	CASE
		WHEN score > 100 OR score < 0 THEN SELECT '成绩输入错误，请输入 0-100 的数字';
		WHEN score <= 100 THEN SELECT 'A';
		WHEN score < 90 THEN SELECT 'B';
		WHEN score < 80 THEN SELECT 'C';
		ELSE SELECT 'D';
	END CASE;
END $;

CALL test_case(90);

-- 3. IF结构
/*
	功能：实现多重分支
	语法：
		IF 条件1 THEN 语句1;
		ELSEIF 条件2 THEN 语句2;
		...
		[ELSE 语句n;]
		END IF;
		
	应用在BEGIN END中
*/

-- 根据传入的成绩，来显示登记，比如传入的成绩：90-100，返回A；80-90，返回B；60-80，返回C；否则，返回D
DELIMITER $
CREATE FUNCTION test_if(score DOUBLE) RETURNS VARCHAR(32)
BEGIN
	IF score > 100 OR score < 0 THEN RETURN '成绩输入错误，请输入 0-100 的数字';
	ELSEIF score <= 100 THEN RETURN 'A';
	ELSEIF score < 90 THEN RETURN 'B';
	ELSEIF score < 80 THEN RETURN 'C';
	ELSE RETURN 'D';
	END IF;
END $;

SELECT test_if(90.5);


-- 二、循环结构
/*
	分类：
		WHILE, LOOP, REPEAT
		
	循环控制：
		ITERATE	类似于 continue
		LEAVE	类似于 break
*/

-- 1. while
/*
	语法：
		[标签: ]WHILE 循环条件 DO
			循环体;
		END WHILE [标签];
*/

-- 2. loop
/*
	语法：
		[标签: ]LOOP
			循环体;
		END LOOP [标签];
*/

-- 3. repeat
/*
	语法：
		[标签：]repeat
			循环体;
		UNTIL 结束循环的条件
		END REPEAT [标签];
*/


-- 没有添加循环控制语句
-- 案例1：批量插入，根据次数插入到admin表中多条记录
DROP PROCEDURE IF EXISTS pro_while1;
DELIMITER $
CREATE PROCEDURE pro_while1(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	WHILE i <= insertCount DO
		INSERT INTO admin(username, `password`) VALUES(CONCAT('Rose', i), CONCAT('000', i));
		SET i = i + 1;
	END WHILE;
END $

CALL pro_while1(100);
SELECT * FROM admin;

-- 2. 添加LEAVE语句
DROP PROCEDURE IF EXISTS pro_while1;
DELIMITER $
CREATE PROCEDURE pro_while1(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	a: WHILE i <= insertCount DO
		INSERT INTO admin(username, `password`) VALUES(CONCAT('jack', i), CONCAT('000', i));
		IF i >= 20 THEN LEAVE a;
		END IF;
		SET i = i + 1;
	END WHILE a;
END $;