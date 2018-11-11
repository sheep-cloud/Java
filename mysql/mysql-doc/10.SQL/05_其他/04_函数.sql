-- 函数
 /*
	含义：一组预先编译好的sql语句的集合，理解成批处理语句
	好处：
		1. 提高代码的重用性
		2. 简化操作
		3. 减少了编译次数并且减少了和数据库服务器的连接次数，提高了效率
		
	区别：
		存储过程：	可以有0个返回，也可以有多个返回；适合做批量插入、批量更新
		函数：		有且仅有1个返回；适合做处理数据后返回一个结果
*/
-- 一、创建函数语法
 /*
	CREATE FUNCTION 函数名(参数列表) RETURNS 返回类型;
	BEGIN
		函数体
	END
	
	注意：
		1. 参数列表 	包含两部分：参数名 参数类型
		2. 函数体	肯定会有return语句，如果没有会报错；如果return语句没有放在函数体的最后也不报错，但不建议
		3. 函数体中仅有一句话，则可以省略BEGIN END;
		4. 使用DELIMITER语句设置结束标记。
*/
-- 案例演示
-- 1. 无参有返回
-- 案例1：返回公司的员工个数
USE myemployees;

DELIMITER $
CREATE FUNCTION myf1() RETURNS INT
BEGIN
	DECLARE c INT DEFAULT 0;	-- 定义局部变量
	SELECT COUNT(*) INTO c
	FROM employees;
	RETURN c;
END $;

SELECT myf1();

-- 2. 有参有返回
-- 案例1：根据员工名，返回它的工资
SELECT * FROM employees;
DELIMITER $
CREATE FUNCTION myf2(empName VARCHAR(20)) RETURNS DOUBLE
BEGIN
	SET @sal = 0;			-- 定义用户变量
	SELECT e.salary INTO @sal	-- 赋值
	FROM employees e
	WHERE e.last_name = empName;
	RETURN @sal;
END $;

SELECT myf2('Austin');

-- 案例3：根据部门名，返回该部门的平均工资
SELECT * FROM departments;
DELIMITER $
CREATE FUNCTION myf3(departmentName VARCHAR(3)) RETURNS DOUBLE
BEGIN
	DECLARE sal DOUBLE;		-- 定义局部变量
	SELECT AVG(salary) INTO sal
	FROM employees e
	INNER JOIN departments d ON d.department_id = e.department_id
	WHERE d.department_name = departmentName;
	RETURN sal;
END $;

SELECT myf3('Hum');

-- 二、查看函数
SHOW CREATE FUNCTION myf3;

-- 三、删除函数
DROP FUNCTION myf3;

-- 案例
-- 1. 创建函数，实现传入两个double，返回二者之和
DELIMITER $
CREATE FUNCTION myf4(num1 DOUBLE, num2 DOUBLE) RETURNS DOUBLE
BEGIN
	SET @sum = num1 + num2;
	RETURN @sum;
END $;

SELECT myf4(5.1, 5.2) result;
DROP FUNCTION myf4;