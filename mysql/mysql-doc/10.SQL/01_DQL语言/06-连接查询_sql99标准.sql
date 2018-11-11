-- 进阶6：连接查询_sql99标准
 /*
	语法：
		SELECT
			查询列表
		FROM
			表1 别名
			[连接类型] JOIN 表2 别名 ON 连接条件
		[WHERE 筛选条件]
		[GROUP BY 分组字段]
		[HAVING 子句]
		[ORDER BY 排序字段];
		
	分类：
		内连接：INNER
		外连接：
			左外：LEFT [OUTER]
			右外：RIGHT [OUTER]
			全外：FULL [OUTER]
		交叉连接：CROSS 笛卡尔乘积
		
	sql92 和 sql99 pk
	功能：	sql99支持的较多
	可读性：sql99实现连接条件和筛选条件的分离，可读性较高
*/

-- 1. 内连接
 /*
	语法：
		SELECT
			查询列表
		FROM 表1 别名
		INNER JOIN 表2 别名 ON 连接条件;
		
	分类：
		等值连接
		非等值连接
		自连接
*/

-- 1.1. 等值连接
-- 案例1：查询员工名和对应的部门名
SELECT
    e.last_name, d.department_name
FROM
    employees e
    INNER JOIN departments d ON e.department_id = d.department_id;

-- 案例2：查询名字中包含e的员工名和工种名；添加筛选
SELECT
    e.last_name, j.job_title
FROM
    employees e
    INNER JOIN jobs j ON e.job_id = j.job_id
WHERE e.last_name LIKE '%e%';

-- 案例3：查询部门个数>3的城市名和部门个数；添加分组、筛选
-- 3.1. 查询每个城市的部门个数
-- 3.2. 在3.1.的结果上筛选满足条件
 SELECT
    l.city, COUNT(*) 部门个数
FROM
    departments d
    INNER JOIN locations l ON d.location_id = l.location_id
GROUP BY l.city
HAVING 部门个数 > 3;

-- 案例4：查询哪个部门的员工个数>3的部门名和员工个数，并按个数降序；添加排序
SELECT
    d.department_name, COUNT(*) 员工个数
FROM
    employees e
    INNER JOIN departments d ON e.department_id = d.department_id
GROUP BY d.department_name
HAVING 员工个数 > 3
ORDER BY 员工个数 DESC;

-- 案例5：查询员工名、部门名、工种名，并按部门名降序
SELECT
    e.last_name, d.department_name, j.job_title
FROM
    employees e
    INNER JOIN departments d ON e.department_id = d.department_id
    INNER JOIN jobs j ON e.job_id = j.job_id
ORDER BY d.department_name DESC;

-- 1.2. 非等值连接
-- 案例1：查询员工的工资级别
SELECT
    e.last_name, e.salary, jg.grade_level
FROM
    employees e
    INNER JOIN job_grades jg ON e.salary BETWEEN jg.lowest_sal AND jg.highest_sal;
    
-- 案例2：查询员工工资级别的个数>20的个数，按工资级别降序
SELECT
    e.last_name, e.salary, jg.grade_level, COUNT(*) 工资级别个数
FROM
    employees e
    INNER JOIN job_grades jg ON e.salary BETWEEN jg.lowest_sal AND jg.highest_sal
GROUP BY jg.grade_level
HAVING 工资级别个数 > 20
ORDER BY jg.grade_level DESC;

-- 1.3. 自连接
-- 案例1：查询员工名和上级的名称
SELECT
    e1.employee_id, e1.last_name, e2.employee_id, e2.last_name
FROM
    employees e1
    INNER JOIN employees e2 ON e1.manager_id = e2.employee_id;
    
-- 案例1：查询姓名中包含k的员工名和上级的名称
SELECT
    e1.employee_id, e1.last_name, e2.employee_id, e2.last_name
FROM
    employees e1
    INNER JOIN employees e2 ON e1.manager_id = e2.employee_id
WHERE e1.last_name LIKE '%k%';


-- 2. 外连接
/*
	应用场景：用于查询一个表中有，另一个表中没有的记录
	
	特点：
		1. 外连接的查询结果为主表中的所有记录，
			如果从表中有和它匹配的，则显示匹配的值
			如果从表中没有和它匹配的，则显示NULL
			外连接查询结果 = 内连接结果 + 主表中有而从表中没有的记录
		2. 左外连接，LEFT JOIN 左边的是主表
		   右外连接，RIGHT JOIN 右边的是主表
		3. 左外和右外交换两个表的顺序，可以实现同样的效果
		4. 全外连接 = 内连接结果 + 表1中有但表2中没有的 + 表2中有但表1中没有的
*/

-- 左外连接
-- 案例1：查询男朋友不在男生表的女生名
SELECT * FROM beauty;
SELECT * FROM boys;
SELECT
    g.id, g.beautyName, b.id
FROM
    beauty g
    LEFT JOIN boys b ON g.boyfriend_id = b.id
WHERE b.id IS NULL;

-- 案例2：查询哪个部门没有员工
SELECT
    d.department_id, d.department_name, e.department_id
FROM
    departments d
    LEFT JOIN employees e ON d.department_id = e.department_id
WHERE e.department_id IS NULL;

-- 交叉连接
SELECT
    g.*, b.*
FROM
    beauty g
    CROSS JOIN boys b;
    
-- 连接查询案例
-- 1. 查询编号>3的女生的男朋友信息，如果有则列出详情，如果没有，则NULL填充
SELECT
    g.id, g.beautyName, b.*
FROM
    beauty g
    LEFT JOIN boys b ON g.boyfriend_id = b.id
WHERE g.id > 3;

-- 2. 查询哪个城市没有部门
SELECT
    l.location_id, l.city, d.location_id
FROM
    locations l
    LEFT JOIN departments d ON l.location_id = d.location_id
WHERE d.department_id IS NULL;

-- 3. 查询部门名为SAL或IT的员工信息
SELECT
    d.department_id, d.department_name, e.*
FROM
    departments d
    LEFT JOIN employees e ON d.department_id = e.department_id
WHERE d.department_name IN ('SAL', 'IT');
