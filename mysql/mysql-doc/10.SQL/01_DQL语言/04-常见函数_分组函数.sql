-- 进阶4：常见函数_分组函数
/*
	功能：用于统计使用，又称为聚合函数或统计函数
	
	分类：
		SUM 求和, AVG 平均值, MAX 最大值, MIN 最小值, COUNT 计算个数
		
	特点：
		1. SUM, AVG 一般用于处理数值型；MAX, MIN, COUNT 可以处理任何类型
		2. 以上分组函数都忽略NULL值
		3. 可以和DISTINCT搭配实现去重的运算
		4. COUNT函数：一般使用COUNT(*)用作统计行数
			4.1. 效率
				MYISAM 存储引擎下，COUNT(*)的效率高
				INNODB 存储引擎下，COUNT(*)和COUNT(1)的效率差不多，比COUNT(字段)要高一些
		5. 和分组函数一同查询的字段要求是GROUP BY后的字段
*/
USE mysql_base;
 
-- 1. 简单的使用
SELECT SUM(e.salary) FROM employees e;
SELECT AVG(e.salary) FROM employees e;
SELECT MAX(e.salary) FROM employees e;
SELECT MIN(e.salary) FROM employees e;
SELECT COUNT(e.salary) FROM employees e;

SELECT
    SUM(e.salary) 工资总和, ROUND(AVG(e.salary), 2) 平均工资, MAX(e.salary) 最高工资, MIN(e.salary) 最低工资, COUNT(e.salary) 工资个数
FROM
    employees e;
 
-- 2. 参数支持哪些类型
SELECT SUM(e.last_name), AVG(e.last_name) FROM employees e;
SELECT MAX(e.last_name), MIN(e.last_name) FROM employees e;
SELECT MAX(e.hiredate), MIN(e.hiredate) FROM employees e;
SELECT COUNT(e.last_name) FROM employees e;

-- 3. 是否忽略NULL
SELECT
    e.employee_id, e.last_name, e.commission_pct
FROM
    employees e;

SELECT
    SUM(e.commission_pct), AVG(e.commission_pct)
FROM
    employees e;

-- 4. 和DISTINCT搭配
SELECT
    SUM(DISTINCT e.salary), SUM(e.salary)
FROM
    employees e;
    
SELECT
    COUNT(DISTINCT e.salary), COUNT(e.salary)
FROM
    employees e;

-- 5. COUNT函数的详细介绍
SELECT COUNT(e.salary) FROM employees e;
SELECT COUNT(*) FROM employees e;
SELECT COUNT(1) FROM employees e;

-- 6. 和分组函数一同查询的字段有限制
SELECT
    e.employee_id, AVG(e.salary)
FROM
    employees e;