-- 进阶6：连接查询_sql92标准
/*
	含义：又称多表查询，当查询的字段来自于多个表时，就会用到连接查询
	
	笛卡尔乘积现象：表1 有m行，表2有n行，结果 = m * n 行
	
	发生原因：当查询多个表时，没有添加有效的连接条件，导致多个表所有行实现完全连接
	如何避免：添加有效的连接条件
	
	分类：
		按年代分类：
			sql92标准：仅仅支持内连接
					等值连接
						1. 多表等值连接的结果为多表的交集部分
						2. n表连接，至少需要n-1个连接条件
						3. 多表的顺序没有要求
						4. 一般需要为表起别名
						5. 可以搭配所有子句使用，比如：筛选、分组、排序。。。
					非等值连接
					自连接
			sql99标准：支持内连接、外连接（左外和右外）、交叉连接
		按功能分类：
			内连接：
				等值连接
				非等值连接
				自连接
			外连接：
				左外连接
				右外连接
				全外连接
			交叉连接
			
		sql92语法：
			内连接：
				等值连接：
					SELECT
						查询列表
					FROM
						表1 别名, 表2 别名
					WHERE 表1.key = 表2.key
					[AND 筛选条件]
					[GROUP BY 分组字段]
					[HAVING 子句]
					[ORDER BY 排序字段];
				非等值连接：
					SELECT
						查询列表
					FROM
						表1 别名, 表2 别名
					WHERE 非等值的连接条件;
				自连接：
					SELECT
						查询列表
					FROM
						表1 别名, 表2 别名
					WHERE 等值的连接条件;
*/
USE mysql_base;
SELECT * FROM boys;
SELECT * FROM girls;

-- 1. sql92标准

-- 1.1. 等值连接

-- 案例1：查询女生名和对应的男生名
SELECT
    b.boy_id, b.boy_name,
    g.girl_id, g.girl_name
FROM
    boys b, girls g
WHERE b.boy_id = g.boyfriend_id;

-- 案例2：查询员工名和对应的部门名
SELECT
    e.employee_id, e.last_name,
    d.department_id, d.department_name
FROM
    employees e, departments d
WHERE e.department_id = d.department_id;

-- 案例3：查询员工名、工种号、工种名
/*
	为表起别名
		1. 提高语句的简洁度
		2. 区分多个重名的字段
		
	注意：如果为表起了别名，则查询的字段就不能使用原来的表名去限定
*/
SELECT
    e.employee_id, e.last_name,
    j.job_id, j.job_title
FROM
    employees e, jobs j
WHERE e.job_id = j.job_id;

-- 1.2. 非等值连接

-- 案例1：查询员工的工资和工资级别，按工资级别排序
SELECT
    e.employee_id, e.last_name, e.salary,
    jg.grade_level
FROM
    employees e, job_grades jg
WHERE e.salary BETWEEN jg.lowest_sal AND jg.highest_sal
ORDER BY jg.grade_level;

-- 1.3. 自连接

-- 案例1：查询员工名和上级的名称
SELECT
    e1.employee_id, e1.last_name,
    e2.employee_id, e2.last_name
FROM
    employees e1, employees e2
WHERE e1.manager_id = e2.employee_id;