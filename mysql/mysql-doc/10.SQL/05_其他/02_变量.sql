-- 变量
/*
	分类：
		1. 系统变量：
			1.1. 全局变量
				作用域：服务器每次启动将为所有的全局变量赋初始值，针对于所有的会话（连接）有效，但不能跨重启
			1.2. 会话变量
				作用域：仅仅针对于当前会话（连接）有效
		2. 自定义变量
			2.1. 用户变量
				作用域：仅仅针对于当前会话（连接）有效。应用在任何地方，也就是BEGIN END里面或BEGIN END的外面
			2.2. 局部变量
				作用域：仅仅在定义它的BEGIN END中有效。应用在BEGIN END中的第一句话
				
			用户变量对比局部变量：
						作用域			定义和使用的位置			语法
				用户变量	当前会话		会话中的任何地方			需要加@符号，不用限定类型
				局部变量	BEGIN END中		只能在BEGIN END中，且为第一句话		一般不用加@符号，需要限定类型
*/

-- 一、系统变量
/*
	说明：变量由系统提供，不是用户定义，属于服务器层面
	语法：
		1. 查看所有的系统变量
			SHOW GLOBAL|[SESSION] VARIABLES;
		2. 查看满足条件的部分系统变量
			SHOW GLOBAL|[SESSION] VARIABLES LIKE '%char%';
		3. 查看指定的某个系统变量的值
			SELECT @@GLOBAL|[SESSION].系统变量名;
		4. 为某个系统变量赋值
			方式一：SET GLOBAL|[SESSION] 系统变量名 = 值;
			方式二：SET @@GLOBAL|[SESSION].系统变量名 = 值;
	注意：如果是全局级别，则需要加GLOBAL；如果是会话级别，则需要加SESSION；如果什么都不写，则默认SESSION。
*/

-- 1. 全局变量
-- 1.1. 查看所有的全局变量
SHOW GLOBAL VARIABLES;

-- 1.2. 查看部分的全局变量
SHOW GLOBAL VARIABLES LIKE '%char%';

-- 1.3. 查看指定的全局变量的值
SELECT @@GLOBAL.autocommit;
SELECT @@tx_isolation;

-- 1.4. 为某个指定的全局变量赋值
SET GLOBAL autocommit = 0;
SET @@GLOBAL.autocommit = 1;

-- 2. 会话变量
-- 2.1. 查看所有的会话变量
SHOW SESSION VARIABLES;
SHOW VARIABLES;

-- 2.2. 查看部分的会话变量
SHOW VARIABLES LIKE '%char%';
SHOW SESSION VARIABLES LIKE '%char%';

-- 2.3. 查看指定的某个会话变量
SELECT @@tx_isolation;
SELECT @@SESSION.tx_isolation;

-- 2.4. 为某个会话变量赋值
SET SESSION tx_isolation = 'read-uncommitted';
SET @@SESSION.tx_isolation = 'REPEATABLE-READ';


-- 二、自定义变量
-- 1. 用户变量
/*
	说明：变量是用户自定义的，不是由系统的。
	使用步骤：
		声明, 赋值, 使用（查看、比较、运算等）
*/
-- 1.1. 声明并初始化。赋值的操作符：= 或 :=
/*
	SET @用户变量名 = 值;
	SET @用户变量名 := 值;
	SELECT @用户变量名 := 值;
*/

-- 1.2. 赋值（更新用户变量的值）
/*
	方式一：通过SET或SELECT
		SET @用户变量名 = 值;
		SET @用户变量名 := 值;
		SELECT @用户变量名 := 值;
	方式二：通过SELECT INTO
		SELECT 字段 INTO 用户变量名 FROM 表;
*/

-- 1.3. 查看
/*
	SELECT @用户变量名;
*/

-- 案例：
-- 1. 声明并初始化
SET @name = 'john';
SET @count = 1;

-- 2. 赋值
SET @name = 100;
SELECT COUNT(*) INTO @count FROM employees;

-- 3. 查看
SELECT @name;
SELECT @count;

-- 2. 局部变量
/*
	
*/
-- 2.1. 声明
/*
	DECLARE 变量名 类型;
	DECLARE 变量名 类型 DEFAULT 值;
*/

-- 2.2. 赋值
/*
	方式一：通过SET或SELECT
		SET 局部变量名 = 值;
		SET 局部变量名 := 值;
		SELECT @局部变量名 := 值;
	方式二：通过SELECT INTO
		SELECT 字段 INTO 局部变量名 FROM 表;
*/

-- 2.3. 使用
/*
	SELECT 局部变量名;
*/

-- 案例：声明两个变量并赋初始值，求和，并打印
-- 1. 用户变量
SET @m = 1;
SET @n = 2;
SET @sum = @m + @n;
SELECT @sum;

-- 2. 局部变量
DELIMITER $
BEGIN
DECLARE m INT DEFAULT 1;
DECLARE n INT DEFAULT 2;
DECLARE SUM INT;
SET SUM = m + n;
SELECT SUM;
END $