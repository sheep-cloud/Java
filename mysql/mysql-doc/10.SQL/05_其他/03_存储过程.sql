-- 存储过程和函数
/*
	存储过程和函数：类似于java中的方法
	好处：
		1. 提高代码的重用性
		2. 简化操作 
*/

-- 存储过程
/*
	含义：一组预先编译好的sql语句的集合，理解成批处理语句
	好处：
		1. 提高代码的重用性
		2. 简化操作
		3. 减少了编译次数并且减少了和数据库服务器的连接次数，提高了效率
*/

-- 一、创建存储过程
/*
	CREATE PROCEDURE 存储过程名（参数列表）
	BEGIN
		存储过程体（一组合法的sql语句）
	END
	
	注意：
		1. 参数列表包含三部分
				参数模式	参数名		参数类型
			举例：	IN		stu_name	VARCHAR(20)
			
			参数模式：
				IN	该参数可以作为输入，也就是该参数需要调用方传入值
				OUT	该参数可以作为输出，也就是该参数可以作为返回值
				INOUT	改参数既可以作为输入又可以作为输出，也就是该参数既需要传入值，又可以返回值
				
		2. 如果存储过程体仅仅只有一句话，BEGIN END可以省略。
			存储过程体中的每条sql语句的结尾要求必须加分号。
			存储过程的结尾可以使用 DELIMITER 重新设置
			语法：
				DELIMITER 结束标记
			案例：
				DELIMITER $
		
*/

-- 二、调用存储过程
/*
	CALL 存储过程名(实参列表);
*/

-- 1. 空参列表
-- 案例：插入到admin表中五条记录
SELECT * FROM admin;

DELIMITER $
CREATE PROCEDURE myp1()
BEGIN
	INSERT INTO admin(username, `password`) VALUES
	('join1', '0000'),
	('lily', '0000'),
	('rose', '0000'),
	('jack', '0000'),
	('tom', '0000');
END $;

-- 调用
CALL myp1;

-- 2. 创建带IN模式参数的存储过程
-- 案例1：创建存储过程，实现根据女生名，查询对应的男生信息
DELIMITER $
CREATE PROCEDURE myp2(IN beautyName VARCHAR(20))
BEGIN
	SELECT b.* FROM boys b
	RIGHT JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

-- 调用
CALL myp2('赵敏');

-- 案例2：创建存储过程，实现用户是否登录成功
DELIMITER $
CREATE PROCEDURE myp3(IN username VARCHAR(10), IN `password` VARCHAR(10))
BEGIN
	DECLARE result INT DEFAULT 0; 					-- 声明变量并初始化
	
	SELECT COUNT(*) INTO result					-- 赋值
	FROM admin a
	WHERE a.username = username AND a.password = `password`;
	
	SELECT IF(result > 0, '登录成功', '登录失败') AS 登录状态;	-- 使用
END $;

-- 调用
CALL myp3('张飞', '8888');
CALL myp3('john', '8888');

-- 3. 创建带OUT模式的存储过程
-- 案例1：根据女生名，返回对应的男生名
DELIMITER $
CREATE PROCEDURE myp4(IN beautyName VARCHAR(50), OUT boyName VARCHAR(20))
BEGIN
	SELECT b.boyName INTO boyName
	FROM boys b
	INNER JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

-- 调用
SET @bName='';	-- 不定义也可以
CALL myp4('小昭', @bName);
SELECT @bName;

-- 案例2：根据女生名，返回对应的男生名和男生魅力值
DELIMITER $
CREATE PROCEDURE myp5(IN beautyName VARCHAR(50), OUT boyName VARCHAR(20), OUT userCP INT)
BEGIN
	SELECT b.boyName, b.userCP INTO boyName, userCP
	FROM boys b
	INNER JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

CALL myp5('小昭', @bName, @userCP);
SELECT @bName, @userCP;

-- 4. 创建带INOUT模式的存储过程
-- 案例1：传入a和b两个值，最终a和b都翻倍并返回
DELIMITER $
CREATE PROCEDURE myp6(INOUT a INT, INOUT b INT)
BEGIN
	SET a = a * 2;
	SET b = b * 2;
END $;

-- 调用
SET @m = 10;
SET @n = 20;
CALL myp6(@m, @n);
SELECT @m, @n;


-- 三、删除存储过程
/*
	语法：DROP PROCEDURE 存储过程名;
*/
DROP PROCEDURE IF EXISTS myp1;

-- 四、查看存储过程的信息
SHOW CREATE PROCEDURE myp2;


-- 存储过程案例
-- 1. 创建存储过程实现传入用户名和密码，插入到acmin表中
DELIMITER $
CREATE PROCEDURE test_pro1(IN username VARCHAR(10), IN `password` VARCHAR(10))
BEGIN
	INSERT INTO admin(username, `password`) VALUES(username, `password`);
END $;

SET @username = 'jax';
SET @password = '0000';
CALL test_pro1(@username, @password);
CALL test_pro1('泰达米尔', '0000');
SELECT * FROM admin;

-- 2. 创建存储过程或函数实现传入女生编号，返回女生名和女生电话
DELIMITER $
CREATE PROCEDURE test_pro2(IN id INT, OUT beautyName VARCHAR(50), OUT phone VARCHAR(11))
BEGIN
	SELECT g.beautyName, g.phone INTO beautyName, phone
	FROM beauty g
	WHERE g.id = id;
END $;

CALL test_pro2('2', @beautyName, @phone);
SELECT @beautyName, @phone;

-- 3. 创建存储过程或函数实现传入两个女生生日，返回大小
DELIMITER $
CREATE PROCEDURE test_pro3(IN birth1 DATETIME, IN birth2 DATETIME, OUT result INT)
BEGIN
	SELECT DATEDIFF(birth1, birth2) INTO result;
END $;

CALL test_pro3('2018-11-01', NOW(), @result);
SELECT @result;

-- 4. 创建存储过程或者函数实现传入一个日期，格式化成xx年xx月xx日并返回
DELIMITER $
CREATE PROCEDURE test_pro4(IN myDate DATETIME, OUT strDate VARCHAR(32))
BEGIN
	SELECT DATE_FORMAT(myDate, '%y年%m月%d日') INTO strDate;
END $;

CALL test_pro4(NOW(), @str);
SELECT @str;

-- 5. 创建存储过程或函数实现传入女生名称，返回：女生 and 男生 格式的字符串
DELIMITER $
CREATE PROCEDURE test_pro5(IN beautyName VARCHAR(50), OUT result VARCHAR(32))
BEGIN
	SELECT CONCAT(IFNULL(g.beautyName, ''), ' AND ', IFNULL(b.boyName, 'NULL')) INTO result
	FROM beauty g
	LEFT JOIN boys b ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

CALL test_pro5('柳岩', @result);
SELECT @result;

DROP PROCEDURE IF EXISTS test_pro5;

-- 6. 创建存储过程或函数，根据传入的条目数和起始索引，查询beauty表的记录
DELIMITER $
CREATE PROCEDURE test_pro6(IN size INT, IN startIndex INT)
BEGIN
	SELECT * FROM beauty
	LIMIT startIndex, size;
END $;

CALL test_pro6(3, 5);