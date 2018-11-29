/*
	1. 统计每月入职的员工数
	2. 统计姓名相同的员工数
	3. 统计入职日期相同的员工数
	4. 姓名相同的员工，只保留入职时间最晚的数据
	5. 入职时间同一天的员工，只保留入职时间最晚的数据
*/

SELECT * FROM employees;

# 1. 统计每月入职的员工数
SELECT
    COUNT(*), DATE_FORMAT(e.hiredate, '%Y-%m') 入职日期
FROM
    employees e
GROUP BY 入职日期;

# 2. 统计姓名相同的员工数
SELECT
    COUNT(*), e.last_name
FROM
    employees e
GROUP BY e.last_name
HAVING COUNT(*) > 1;

# 3. 统计入职日期相同的员工数
SELECT
    COUNT(*), DATE(e.hiredate) 入职日期
FROM
    employees e
GROUP BY 入职日期
HAVING COUNT(*) > 1;

# 4. 姓名相同的员工，只保留入职时间最晚的数据
SELECT
    e.*
FROM
    employees e
GROUP BY e.last_name
HAVING MAX(e.hiredate);

# 5. 入职时间同一天的员工，只保留入职时间最晚的数据
SELECT
    e.*, DATE(e.hiredate) 入职日期
FROM
    employees e
GROUP BY 入职日期
HAVING MAX(e.hiredate);