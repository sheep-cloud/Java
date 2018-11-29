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
 
-- 1. 简单的使用
SELECT SUM(salary) FROM employees;
SELECT AVG(salary) FROM employees;
SELECT MAX(salary) FROM employees;
SELECT MIN(salary) FROM employees;
SELECT COUNT(salary) FROM employees;
 
SELECT SUM(salary) 工资总和, ROUND(AVG(salary), 2) 平均工资, MAX(salary) 最高工资, MIN(salary) 最低工资, COUNT(salary) 工资个数 FROM employees;
 
-- 2. 参数支持哪些类型
SELECT SUM(last_name), AVG(last_name) FROM employees;
SELECT MAX(last_name), MIN(last_name) FROM employees;
SELECT MAX(hiredate), MIN(hiredate) FROM employees;
SELECT COUNT(last_name) FROM employees;

-- 3. 是否忽略NULL
SELECT commission_pct FROM employees;
SELECT SUM(commission_pct), AVG(commission_pct) FROM employees;

-- 4. 和DISTINCT搭配
SELECT SUM(DISTINCT salary), SUM(salary) FROM employees;
SELECT COUNT(DISTINCT salary), COUNT(salary) FROM employees;

-- 5. COUNT函数的详细介绍
SELECT COUNT(salary) FROM employees;
SELECT COUNT(*) FROM employees;
SELECT COUNT(1) FROM employees;

-- 6. 和分组函数一同查询的字段有限制
SELECT AVG(salary), employee_id FROM employees;