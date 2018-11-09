-- 进阶1：基础查询
 /*
	语法：
		SELECT
			查询列表
		FROM
			表名;
			
	执行顺序：
		FROM, SELECT
		
	特点：
		1. 查询列表可以是：表中的字段、常量值、表达式、函数
		2. 查询的结果是一个虚拟的表格
*/

-- 1. 查询表中的单个字段
SELECT last_name FROM employees;

-- 2. 查询表中的多个字段
SELECT last_name, salary, email FROM employees;

-- 3. 查询表中的多个字段
SELECT employee_id, first_name, last_name, email, phone_number, job_id, salary, commission_pct, manager_id, department_id, hiredate FROM employees;

SELECT * FROM employees;

-- 4. 查询常量值
SELECT 100;
SELECT 'john';

-- 5. 查询表达式
SELECT 100 % 98;

-- 6. 查询函数
SELECT VERSION();

-- 7. 别名；便于理解、区分重名
SELECT 100 % 98 AS 结果;
SELECT last_name AS 姓, first_name AS 名 FROM employees;
SELECT last_name 姓, first_name 名 FROM employees;

-- 案例：查询salary，显示结果为 out put
SELECT salary AS 'out put' FROM employees;

-- 8. 去重；案例：查询员工表中涉及到的所有的部门编号
SELECT DISTINCT department_id FROM employees;

-- 9. + 号的作用
/*
	java中的+号：
		1. 运算符：两个操作数为数值型
		2. 连接符：只要有一个操作数为字符串
		
	mysql中的+号：
		仅仅只有一个功能：运算符
		SELECT 100 + 90;	两个操作数都未数值型，则做加法运算
		SELECT '100' + 90;	其中一方为字符串，试图将字符型数值转换为数值型
						转换成功：继续做加法运算
						转换失败：则将字符型数值转换为0
		SELECT NULL + 90;	只要一种一方为null，则结果肯定为null
*/

-- 案例：查询员工名和姓连接成一个字段，并显示为姓名
SELECT last_name + first_name AS 姓名 FROM employees;
SELECT CONCAT(IFNULL(last_name, ''), IFNULL(first_name, '')) AS 姓名 FROM employees;