-- 视图
/*
	含义：虚拟表，和普通表一样使用
	mysql5.1版本出现的新特性，是通过表动态生成的数据
	
	比如：舞蹈班和普通班级的对比
	
	作用：
		1. 重用sql语句
		2. 简化复杂的sql操作，不必知道它的查询细节
		3. 保护数据，提高安全性
		
	视图和表的对比：
			创建语法的关键字	是否实际占用物理空间	使用
		视图	CREATE VIEW		只是保存了sql逻辑	增删改查，只是一般不能使用增删改
		表	CREATE TABLE		保存了数据		增删改查
*/

-- 案例：查询姓张的学生名和专业名
USE students;
SELECT si.id, si.stu_name, si.major_id, m.major_name FROM stu_info si
INNER JOIN major m ON si.major_id = m.id
WHERE si.stu_name LIKE '张%';

CREATE VIEW v1
AS
SELECT si.id, si.stu_name, si.major_id, m.major_name FROM stu_info si
INNER JOIN major m ON si.major_id = m.id
WHERE si.stu_name LIKE '张%';

-- 查询视图
SELECT * FROM v1;
DROP VIEW v1;


-- 一、创建视图
/*
	语法：
		CREATE VIEW 视图名
		AS
		查询语句;
*/
USE myemployees;
-- 1. 查询姓名中包含a字符的员工名、部门名、工种信息
-- 1.1. 创建视图
CREATE VIEW myv1
AS
SELECT e.employee_id, e.last_name, d.department_id, d.department_name, j.* FROM employees e
INNER JOIN departments d ON d.department_id = e.department_id
INNER JOIN jobs j ON j.job_id = e.job_id;

-- 1.2. 使用视图
SELECT m1.* FROM myv1 m1
WHERE m1.last_name LIKE '%a%';

-- 2. 查询各部门的平均工资级别
-- 2.1. 创建视图；查看每个部门的平均工资
CREATE VIEW myv2
AS
SELECT e.department_id, AVG(e.salary) ag FROM employees e
GROUP BY e.department_id;

-- 2.2. 使用视图
SELECT m2.*, jg.grade_level FROM myv2 m2
INNER JOIN job_grades jg ON m2.ag BETWEEN jg.lowest_sal AND jg.highest_sal;

-- 3. 查询平均工资最低的部门信息
SELECT m2.* FROM myv2 m2
ORDER BY m2.ag
LIMIT 1;

-- 4. 查询平均工资最低的部门名和工资
-- 4.1. 创建视图
CREATE VIEW myv3
AS
SELECT m2.* FROM myv2 m2
ORDER BY m2.ag
LIMIT 1;

-- 4.2. 使用视图
SELECT d.*, m3.ag FROM myv3 m3
INNER JOIN departments d ON d.department_id = m3.department_id;


-- 二、视图的修改
/*
	方式一：
		CREATE OR REPLACE VIEW 视图名
		AS
		查询语句;
	方式二：
		ALTER VIEW 视图名
		AS
		查询语句;
*/


-- 三、删除视图
/*
	语法：
		DROP VIEW 视图名, 视图名,...;
*/


-- 四、查看视图
DESC myv3;
SHOW FULL COLUMNS FROM myv3;
SHOW CREATE VIEW myv3;