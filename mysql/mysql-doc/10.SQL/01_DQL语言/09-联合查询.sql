-- 进阶9：联合查询
/*
	UNION 联合，合并：将多条查询语句的结果合并成一个结果
	
	语法：
		查询语句1
		UNION [ALL]
		查询语句2
		UNION [ALL]
		...;
		
	应用场景：
		要查询的结果来自于多个表，且多个表没有直接的连接关系，但查询的信息一致时
		
	特点：
		1. 要求多条查询语句的查询列数是一致的
		2. 要求多条查询语句的查询的每一列的类型与顺序最好一致
		3. UNION关键字默认去重，如果使用UNION ALL可以包含重复项
*/

-- 案例：查询中国用户中男性的信息以及外国用户中男性的用户信息
SELECT id, c_name FROM t_ca WHERE c_sex = '男'
UNION ALL
SELECT t_id, t_name FROM t_ua WHERE t_gender = 'male';