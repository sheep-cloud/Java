-- 进阶2：条件查询
/*
	语法：
		SELECT
			查询列表
		FROM
			表名
		WHERE
			筛选条件;
			
	执行顺序：
		FROM, WHERE, SELECT
		
	分类：
		1. 按条件表达式筛选
			条件运算符：>, <, =, !=, <>, >=, <=
		2. 按逻辑表达式筛选：用于连接条件表达式
			逻辑运算符：&&, ||, !, AND, OR, NOT
		3. 模糊查询
			LIKE, 
				1. 通配符：
					% 任意多个字符，包含0个字符
					_ 任意单个字符
			BETWEEN AND,
				1. 可以提高语句的简洁度
				2. 包含临界值
				3. 两个临界值不能调换顺序
			IN,
				1. 含义：判断某字段的值是否属于IN列表中的某一项
				2. 特点：
					2.1. 可以提高语句的简洁度
					2.2. IN列表的值类型必须一致或兼容
			IS NULL, IS NOT NULL
*/
USE mysql_base;

-- 1. 按条件表达式筛选
-- 案例1：查询工资大于12000的员工信息
SELECT
    e.*
FROM
    employees e
WHERE e.salary > 12000;

-- 案例2：查询部门编号不等于90的员工名和部门编号
SELECT
    e.employee_id, e.last_name, e.department_id
FROM
    employees e
WHERE e.department_id <> 90;

-- 2. 按逻辑表达式筛选
-- 案例1：查询工资在10000到20000之间的员工名、工资以及奖金
SELECT
    e.employee_id, e.last_name, e.salary, e.commission_pct
FROM
    employees e
WHERE e.salary >= 10000 AND e.salary <= 20000;

SELECT
    e.employee_id, e.last_name, e.salary, e.commission_pct
FROM
    employees e
WHERE e.salary BETWEEN 10000 AND 20000;

-- 案例2：查询部门编号不是在90到110之间，或者工资高于15000的员工信息
SELECT
    e.employee_id, e.last_name, e.salary, e.commission_pct
FROM
    employees e
WHERE e.department_id < 90 OR e.department_id > 110 OR e.salary > 15000;

SELECT
    e.employee_id, e.last_name, e.salary, e.commission_pct
FROM
    employees e
WHERE NOT(e.department_id >= 90 AND e.department_id <= 110) OR e.salary > 15000;

-- 3. 模糊查询
-- 3.1. LIKE
-- 案例1：查询员工名中包含字符a的员工信息
SELECT
    e.*
FROM
    employees e
WHERE e.last_name LIKE '%a%';

-- 案例2：查询员工名中第三个字符为n，第五个字符为l的员工名和工资
SELECT
    e.employee_id, e.last_name, e.salary
FROM
    employees e
WHERE e.last_name LIKE '__n_l%';

-- 案例3：查询员工名中第二个字符为_的员工名
SELECT
    e.employee_id, e.last_name
FROM
    employees e
WHERE e.last_name LIKE '_\_%';

-- 3.2. BETWEEN AND
-- 案例1：查询员工编号在100到120之间的员工信息
SELECT
    e.*
FROM
    employees e
WHERE e.employee_id BETWEEN 100 AND 120;

-- 3.3. IN
-- 案例1：查询员工的工种编号是 IT_PROT、AD_VP、AD_PRES 的其中一个的员工名和工种编号
SELECT
    e.employee_id, e.last_name, e.job_id
FROM
    employees e
WHERE e.job_id IN ('IT_PROT', 'AD_VP', 'AD_PRES');

-- 3.4. IS NULL, IS NOT NULL
-- 案例1：查询没有奖金的员工名和奖金率
SELECT
    e.employee_id, e.last_name, e.commission_pct
FROM
    employees e
WHERE e.commission_pct IS NULL;

-- 安全等于 <=>，不建议使用
SELECT
    e.employee_id, e.last_name, e.commission_pct
FROM
    employees e
WHERE e.commission_pct <=> NULL;