# 1. 为什么要学习数据库

- 数据库的好处
  - 持久化数据到本地
  - 可以实现结构化查询，方便管理

# 2. 数据库的相关概念

- DB：数据库（database）：存储数据的容器。它保存了一系列有组织的数据。
- DBMS：数据库管理系统（DatabaseManagementSystem）：又称为数据库软件或数据库产品，用于创建或管理DB。
- SQL：结构化查询语言（StructureQueryLanguage）：结构化查询语言，用于和数据库通信的语言。

```ini
SQL的优点:
	1. 不是某个特定数据库供应商专有的语言，几乎所有DBMS都支持SQL
	2. 简单易学
	3. 虽然简单，但实际上是一种强有力的语言，灵活使用其语言元素，可以进行非常复杂和高级的数据库操作。
```

# 3. 数据库存储数据的特点

- 数据存放到表中，然后表再放到库中
- 一个库中可以有多张表，每张表具有唯一的表名用来标识自己
- 表中有一个或多个列，列又称为"字段"，相当于java中"属性"
- 表中的每一个行数据，相当于java中"对象"

# 4. 初识MySQL

## 4.1. MySQL的背景

- 前身属于瑞典的一家公司，MySQL AB
- 08年被sun公司收购
- 09年被oracle收购

## 4.2. MySQL的优点

- 开源、免费、成本低
- 性能高、移植性也好
- 体积小，便于安装

## 4.3. 启动和停止MySQL服务

```ini
启动: net start 服务名
停止: net stop 服务名
```

## 4.4. 数据库的使用

```ini
登录: mysql [-h主机 -P端口] -u用户名 -p密码
查看MySQL中有哪些数据库: SHOW DATABASES;
使用一个数据库: USE 数据库名;
查看数据库中有哪些表: SHOW TABLES [FROM 数据库名];
查看表中字段信息: SHOW FULL COLUMNS FROM 表名;
删除表: DROP TABLE 表名;
退出: quit;
```

# 5. DQL语言的学习

## 5.1. 基础查询

```sql
-- 进阶1：基础查询
 /*
	语法：
		SELECT
			查询列表
		FROM
			表名;
		
	特点：
		1. 查询列表可以是：表中的字段、常量值、表达式、函数
		2. 查询的结果是一个虚拟的表格
*/

USE myemployees;

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
```

## 5.2. 条件查询

```sql
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
		FROM、WHERE、SELECT
		
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

USE myemployees;

-- 1. 按条件表达式筛选
-- 案例1：查询工资大于12000的员工信息
SELECT * FROM employees
WHERE salary > 12000;

-- 案例2：查询部门编号不等于90的员工名和部门编号
SELECT last_name, department_id FROM employees
WHERE department_id <> 90;

-- 2. 按逻辑表达式筛选
-- 案例1：查询工资在10000到20000之间的员工名、工资以及奖金
SELECT last_name, salary, commission_pct FROM employees
WHERE salary >= 10000 AND salary <= 20000;

-- 案例2：查询部门编号不是在90到110之间，或者工资高于15000的员工信息
SELECT * FROM employees
WHERE department_id < 90 OR department_id > 110 OR salary > 15000;

SELECT * FROM employees
WHERE NOT(department_id	>= 90 AND department_id <= 110) OR salary > 15000;

-- 3. 模糊查询
-- 3.1. LIKE
-- 案例1：查询员工名中包含字符a的员工信息
SELECT * FROM employees
WHERE last_name LIKE '%a%';

-- 案例2：查询员工名中第三个字符为n，第五个字符为l的员工名和工资
SELECT last_name, salary FROM employees
WHERE last_name LIKE '__n_l%';

-- 案例3：查询员工名中第二个字符为_的员工名
SELECT last_name FROM employees
WHERE last_name LIKE '_\_%';

-- 3.2. BETWEEN AND
-- 案例1：查询员工编号在100到120之间的员工信息
SELECT * FROM employees
WHERE employee_id BETWEEN 100 AND 120;

-- 3.3. IN
-- 案例1：查询员工的工种编号是 IT_PROT、AD_VP、AD_PRES 的其中一个的员工名和工种编号
SELECT last_name, job_id FROM employees
WHERE job_id IN ('IT_PROT', 'AD_VP', 'AD_PRES');

-- 3.4. IS NULL, IS NOT NULL
-- 案例1：查询没有奖金的员工名和奖金率
SELECT last_name, commission_pct FROM employees
WHERE commission_pct IS NULL;

-- 安全等于 <=>，不建议使用
SELECT last_name, commission_pct FROM employees
WHERE commission_pct <=> NULL;
```

## 5.3. 排序查询

```sql
-- 进阶3：排序查询
/*
	语法：
		SELECT
			查询列表
		FROM
			表名
		[WHERE 筛选条件]
		ORDER BY 排序列表 [ASC|DESC]
		
	执行顺序：
		FROM, WHERE, SELECT, ORDER BY
		
	特点：
		1. ASC 升序，DESC 降序；如果不写，默认是升序
		2. ORDER BY 字句中可以支持单个字段、多个字段、表达式、函数、别名
		3. ORDER BY 字句一般是放在查询语句的最后面，LIMIT 字句除外
		
*/

USE myemployees;

-- 案例1：查询员工信息，要求工资从高到低排序
SELECT * FROM employees
ORDER BY salary DESC;

-- 案例2：查询部门编号大于等于90的员工信息，按照入职时间的先后进行排序；添加筛选条件
SELECT * FROM employees
WHERE department_id >= 90
ORDER BY hiredate ASC;

-- 案例3：按年薪的高低显示员工的信息和年薪；按表达式排序
SELECT *, salary * 12 * (1 + IFNULL(commission_pct, 0)) 年薪 FROM employees
ORDER BY salary * 12 * (1 + IFNULL(commission_pct, 0)) DESC;

-- 案例4：按年薪的高低显示员工的信息和年薪；按别名排序
SELECT *, salary * 12 * (1 + IFNULL(commission_pct, 0)) 年薪 FROM employees
ORDER BY 年薪 DESC;

-- 案例5：按姓名的长度显示员工的姓名和工资；按函数排序
SELECT LENGTH(last_name) 字节长度, last_name, salary FROM employees
ORDER BY 字节长度 DESC;

-- 案例6：查询员工信息，要求先按工资降序排序，再按员工编号升序排序
SELECT * FROM employees
ORDER BY salary DESC, employee_id ASC;
```



# 6. DML语言的学习

# 7. DDL语言的学习

# 8. TCL语言的学习

# 9. 视图的讲解

# 10. 存储过程和函数

# 11. 流程控制结构