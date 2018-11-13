-- 进阶5：分组查询
/*
	语法：
		SELECT
			分组函数, 分组后的字段
		FROM
			表名
		[WHERE 筛选条件]
		GROUP BY 分组字段
		HAVING 子句
		[ORDER BY 子句]
		
	执行顺序：
		FROM, WHERE, GROUP BY, HAVING, SELECT, ORDER BY
		
	注意：
		查询列表必须特殊，要求是分组函数和GROUP BY后面出现的字段
		
	特点：
		1. 分组查询中的筛选条件可以分为两类
			1.1. 分组函数作为条件肯定是放在HAVING子句中
			1.2. 能用分组前筛选的，就优先考虑使用分组前筛选
			
						数据源			位置				关键字
				分组前筛选	原始表			GROUP BY 子句的前面		WHERE
				分组后筛选	分组后的结果集		GROUP BY 子句的后面		HAVING
			
		2. GROUP BY 子句支持单个字段分组，多个字段分组（多个字段之间用逗号分开没有顺序要求），表达式或函数（用的较少）
		3. 也可以添加排序（排序放在整个分组查询的最后）
*/

-- 1. 简单的分组查询

-- 案例1：查询每个工种的最高工资
SELECT MAX(salary), job_id FROM employees
GROUP BY job_id;

-- 案例2：查询每个位置上的部门个数
SELECT COUNT(*), location_id FROM departments
GROUP BY location_id;

-- 2. 添加分组前的筛选条件

-- 案例1：查询邮箱中包含a字符的，每个部门的平均工资
SELECT AVG(salary), department_id FROM employees
WHERE email LIKE '%a%'
GROUP BY department_id;

-- 案例2：查询有奖金的每个领导手下员工的最高工资
SELECT MAX(salary), manager_id FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY manager_id;

-- 3. 添加分组后的筛选条件

-- 案例1：查询哪个部门的员工个数>2
-- 1.1. 查询每个部门的员工个数
SELECT COUNT(*), department_id FROM employees
GROUP BY department_id;

-- 1.2. 根据1.1的结果进行筛选，查询哪个部门的员工个数>2
SELECT COUNT(*), department_id FROM employees
GROUP BY department_id
HAVING COUNT(*) > 2;

-- 案例2：查询每个工种有奖金的员工的最高工资>10000的工种编号和最高工资
-- 2.1. 查询每个工种有奖金的员工的最高工资
SELECT MAX(salary), job_id FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY job_id;

-- 2.2. 根据2.1的结果继续筛选，最高工资>10000
SELECT MAX(salary), job_id FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY job_id
HAVING MAX(salary) > 10000;

-- 案例3：查询领导编号>102的每个领导手下的员工的最低工资>5000的领导编号是哪个，以及其最低工资
-- 3.1. 查询每个领导手下的员工的最低工资
SELECT MIN(salary), manager_id FROM employees
GROUP BY manager_id;

-- 3.2. 添加筛选条件：领导编号>102
SELECT MIN(salary), manager_id FROM employees
WHERE manager_id > 102
GROUP BY manager_id;

-- 3.3. 添加筛选条件：最低工资>5000
SELECT MIN(salary), manager_id FROM employees
WHERE manager_id > 102
GROUP BY manager_id
HAVING MIN(salary) < 5000;

-- 4. 按表达式或函数分组

-- 案例1：按员工姓名的长度分组，查询每一组的员工个数，筛选员工个数>5的有哪些
-- 1.1. 查询每个长度的员工个数
SELECT COUNT(*), LENGTH(last_name) len_name, last_name FROM employees
GROUP BY len_name;

-- 1.2. 添加筛选条件
SELECT COUNT(*), LENGTH(last_name) len_name, last_name FROM employees
GROUP BY len_name
HAVING COUNT(*) > 5;

-- 5. 按多个字段分组

-- 案例1：查询每个部门每个工种的员工的平均工资
SELECT ROUND(AVG(salary), '2') 平均工资, department_id, job_id FROM employees
GROUP BY department_id, job_id;

-- 6. 添加排序

-- 案例1：查询每个部门每个工种的员工的平均工资，并且按平均工资的高低显示
SELECT ROUND(AVG(salary), '2') 平均工资, department_id, job_id FROM employees
GROUP BY department_id, job_id
ORDER BY 平均工资 DESC;