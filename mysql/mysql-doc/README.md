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

- 数据查询语言

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

## 5.4. 常见函数

### 5.4.1. 单行函数

```sql
-- 进阶4：常见函数_单行函数
/*
	概念：类似于java的方法，将一组逻辑语句封装在方法中，对外暴露方法名
	好处：
		1. 隐藏了实现细节
		2. 提高了代码的复用性
	调用：
		SELECT 函数名(实参列表) [FROM 表名];
	特点：
		1. 叫什么（函数名）
		2. 干什么（函数功能）
		
	分类：
		1. 单行函数
			如：CONCAT、LENGTH、IFNULL等
		2. 分组函数
		
		功能：做统计使用，又称为统计函数、聚合函数、组函数
		
	常见函数：
		字符函数：
			LENGTH, CONCAT, SUBSTR, INSTR, TRIM, UPPER, LOWER, LPAD, RPAD, REPLACE
		数学函数：
			ROUND, CEIL, FLOOR, TRUNCATE, MOD
		日期函数：
			NOW, CURDATE, CURTIME, YEAR, MONTH, MONTHNAME, DAY, HOUR, MINUTE, SECOND, STR_TO_DATE, DATE_FORMAT
		其他函数：
			VERSION, DATABASE, USER
		控制函数：
			IF CASE
*/

-- 1. 字符函数

-- 1.1. LENGTH 获取参数自的字节个数
SELECT LENGTH('john');
SELECT LENGTH('杰克jack');
SHOW VARIABLES LIKE '%char%';

-- 1.2. CONCAT 拼接字符串
SELECT CONCAT(last_name, '_', first_name) 姓名 FROM employees;

-- 1.3. UPPER、LOWER
SELECT UPPER('john');
SELECT LOWER('john');

-- 案例：将姓大写，名变小写，然后拼接
SELECT CONCAT(UPPER(last_name), '_', LOWER(first_name)) 姓名 FROM employees;

-- 1.4. SUBSTR、SUBSTRING；索引从1开始
-- 截取从指定索引处后面所有字符
SELECT SUBSTR('李莫愁爱上了陆展元', 7) out_put;
-- 截取从指定索引出指定长度的字符
SELECT SUBSTR('李莫愁爱上了陆展元', 1, 3) out_put;

-- 案例：姓名中首字母大写、其他字符小写然后用_拼接，显示出来
SELECT CONCAT(UPPER(SUBSTR(last_name, 1, 1)), '_', SUBSTR(last_name, 2)) FROM employees;

-- 1.5. INSTR 返回子串第一次出现的索引，如果找不到返回0
SELECT INSTR('杨不悔爱上了殷六侠', '殷六侠') out_put;

-- 1.6. TRIM 去除首位空格或指定字符串
SELECT LENGTH(TRIM('  张翠山  ')) out_put;
SELECT TRIM('a' FROM 'aaaaa张aaaaaaaa翠aa山aaaa') out_put;

-- 1.7. LPAD 用指定的字符实现左填充指定长度
SELECT LPAD('9527', 6, '0') out_put;

-- 1.8. RPAD 用指定的字符实现右填充指定长度
SELECT RPAD('9527', 6, '0') out_put;

-- 1.8. REPLACE 替换
SELECT REPLACE('uziuziuziuziuzi', 'uzi', 'jeckeylove') out_put;


-- 2. 数学函数

-- 2.1. ROUND 四舍五入
SELECT ROUND(-1.55);
SELECT ROUND(1.5678, 2);

-- 2.2. CEIL 向上取整，返回>=该参数的最小整数
SELECT CEIL(-1.002);

-- 2.3. FLOOR 向上下取整，返回<=该参数的最小整数
SELECT CEIL(-9.99);

-- 2.4. TRUNCATE 截断，截断小数点后的位数
SELECT TRUNCATE(1.69999, 2);

-- 2.5. MOD 取余；与%相同
SELECT MOD(10, 4);
SELECT 10 % 4;


-- 3. 日期函数

-- 3.1. NOW 返回当前系统日期+时间
SELECT NOW();

-- 3.2. CURDATE 返回当前系统日期，不包含时间
SELECT CURDATE();

-- 3.3. CURTIME 返回当前时间，不包含日期
SELECT CURTIME();

-- 3.4. 获取指定的部分，年、月、日、小时、分钟、秒
SELECT CONCAT(YEAR(hiredate), '年', LPAD(MONTH(hiredate), 2, '0'), '月', LPAD(DAY(hiredate), 2, '0'), '日') 入职日期 FROM employees;

-- 3.5. STR_TO_DATE 将字符通过指定的格式转换成日期
SELECT STR_TO_DATE('04/03/1992', '%m/%d/%Y') out_put;

-- 案例：查询入职日期04/03/1992的员工信息
SELECT * FROM employees
WHERE hiredate = STR_TO_DATE('04/03/1992', '%m/%d/%Y');

-- 3.6. DATE_FORMAT 将日期转换成字符串
SELECT DATE_FORMAT(NOW(), '%y年%m月%d日') out_put;

-- 案例：查询有奖金的员工名和入职日期（xx月/xx日 xxxx年）
SELECT last_name, DATE_FORMAT(hiredate, '%m月/%d日 %Y年') 入职日期 FROM employees
WHERE commission_pct IS NOT NULL;


-- 4. 其他函数
SELECT VERSION();
SELECT DATABASE();
SELECT USER();


-- 5. 流程控制函数

-- 5.1. IF函数：IF ELSE 的效果
SELECT IF(10 < 5, '大', '小');

SELECT last_name, commission_pct, IF(commission_pct IS NULL, '没奖金，呵呵', '有奖金，嘻嘻') 备注 FROM employees;

-- 5.2. CASE 函数
-- 5.2.1. CASE函数的使用一：类似于 switch case；等值判断
/*
	java：
		switch (变量或表达式) {
			case 常量1: 语句1; break;
			...
			default: 语句n; break;
		}
		
	mysql：
		CASE 要判断的字段或表达式
			WHEN 常量1 THEN 要显示的值1或语句1
			WHEN 常量2 THEN 要显示的值2或语句2
			...
			ELSE 要显示的值n或语句n;
		END
*/
/*
	案例：查询员工的工资情况
	要求：
		部门编号=30，显示工资为1.1倍
		部门编号=40，显示工资为1.2倍
		部门编号=50，显示工资为1.3倍
		其他部门，显示的工资为原工资
*/
SELECT salary 原始工资, department_id,
    CASE department_id
        WHEN 30 THEN salary * 1.1
        WHEN 40 THEN salary * 1.2
        WHEN 50 THEN salary * 1.3
        ELSE salary
    END 新工资
FROM employees;

-- 5.2.2. CASE函数的使用二：类似于 多重if；区间判断
/*
	java：
		if (条件1) {
			语句1;
		} else if (条件2) {
			语句2;
		}
		
	mysql：
		CASE
			WHEN 条件1 THEN 要显示的值1或语句1
			WHEN 条件2 THEN 要显示的值2或语句2
			...
			ELSE 要显示的值n或语句n
		END
*/
/*
	案例：查询员工的工资情况
	要求：
		工资>20000，显示A级别
		工资>15000，显示B级别
		工资>10000，显示C级别
		否则，显示D级别
*/
SELECT salary, department_id,
    CASE
        WHEN salary > 20000 THEN 'A'
        WHEN salary > 15000 THEN 'B'
        WHEN salary > 10000 THEN 'C'
        ELSE 'D'
    END 工资级别
FROM employees;
```

### 5.4.2. 分组函数

```sql
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
```

## 5.5. 分组查询

```sql
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
						数据源			位置					关键字
			分组前筛选	原始表			GROUP BY 子句的前面		WHERE
			分组后筛选	分组后的结果集	 GROUP BY 子句的后面		HAVING
			
			1.1. 分组函数作为条件肯定是放在HAVING子句中
			1.2. 能用分组前筛选的，就优先考虑使用分组前筛选
			
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
SELECT COUNT(*), LENGTH(last_name) len_name FROM employees
GROUP BY len_name;

-- 1.2. 添加筛选条件
SELECT COUNT(*), LENGTH(last_name) len_name FROM employees
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
```

## 5.6. 连接查询

### 5.6.1. sql92标准

```sql
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

SELECT * FROM beauty;
SELECT * FROM boys;

-- 1. sql92标准

-- 1.1. 等值连接

-- 案例1：查询女生名和对应的男生名
SELECT boyName, beautyName FROM boys, beauty
WHERE boys.id = beauty.boyfriend_id;

-- 案例2：查询员工名和对应的部门名
SELECT last_name, department_name FROM employees, departments
WHERE departments.department_id = employees.department_id;

-- 案例3：查询员工名、工种号、工种名
/*
	为表起别名
		1. 提高语句的简洁度
		2. 区分多个重名的字段
		
	注意：如果为表起了别名，则查询的字段就不能使用原来的表名去限定
*/
SELECT e.last_name, j.job_id, j.job_title FROM employees e, jobs j
WHERE j.job_id = e.job_id;

-- 1.2. 非等值连接

-- 案例1：查询员工的工资和工资级别
SELECT e.last_name, e.salary, jg.grade_level FROM employees e, job_grades jg
WHERE e.salary BETWEEN jg.lowest_sal AND jg.highest_sal
ORDER BY jg.grade_level;

-- 1.3. 自连接

-- 案例1：查询员工名和上级的名称
SELECT e1.employee_id, e1.last_name, e2.employee_id, e2.last_name FROM employees e1, employees e2
WHERE e1.manager_id = e2.employee_id;
```

### 5.6.2. sql99标准

```sql
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
```

## 5.7. 子查询

```sql
-- 进阶：子查询
 /*
	含义：
		出现在其他语句中的SELECT语句，成为子查询或内查询
		外部的语句可以是INSERT, UPDATE, DELETE, SELECT等，一般SELECT作为外面语句较多
		外部的查询语句，成为主查询或外查询
		
	分类：
		按子查询出现的位置：
			SELECT 后面
				标量子查询
			FROM 后面
				表子查询
			WHERE 或 HAVING 后面
				标量子查询	（单行）
				列子查询	（多行）
				行子查询
			EXISTS 后面（相关子查询）
				表子查询
				EXISTS(完整的查询)，结果：1或0
			
		按结果集的行列书不同：
			标量子查询（结果集：一行一列）
			列子查询（结果集：多行一列）
			行子查询（结果集：多行多列）
			表子查询（结果集：随意，一般为多行多列）
			
	特点：
		1. 子查询放在小括号内
		2. 子查询一般放在条件的右侧
		3. 标量子查询，一般搭配着单行操作符使用（>, <, >=, <=, =, <>）
		4. 列子查询，一般搭配着多行操作符使用（IN/NOT IN, ANY/SOME, ALL）；IN是= ANY的别名；NOT IN是<> ALL的别名
		5. 子查询的执行优先于主查询执行，主查询的条件用到了子查询的结果
		6. 将子查询结果充当一张表，必须起别名
*/

-- 一：WHERE 或 HAVING 后面

-- 1. 标量子查询

-- 案例1：谁的工资比Abel高？
-- 1.1. 查询Abel的工资
SELECT salary FROM employees
WHERE last_name = 'Abel';

-- 1.2. 查询员工的信息，满足 salary > 1.1
SELECT * FROM employees
WHERE salary > (
    SELECT salary FROM employees
    WHERE last_name = 'Abel'
);

-- 案例2：返回job_id与141号员工相同，salary比143号员工多的员工last_name、job_id、salary
-- 2.1. 查询141号员工job_id
SELECT job_id FROM employees
WHERE employee_id = 141;

-- 2.2. 查询143号员工salary
SELECT salary FROM employees
WHERE employee_id = 143;

-- 2.3. 查询员工的信息，满足 job_id = 1.1，salary > 2.2
SELECT last_name, job_id, salary FROM employees
WHERE job_id = (
    SELECT job_id FROM employees
    WHERE employee_id = 141
) AND salary > (
    SELECT salary FROM employees
    WHERE employee_id = 143
);

-- 案例3：返回公司工资最少的员工的last_name, job_id, salary
-- 3.1. 查询公司的最低工资
SELECT MIN(salary) FROM employees;

-- 3.2. 查询 salary = 3.1 的员工的last_name, job_id, salary
SELECT last_name, job_id, salary FROM employees
WHERE salary = (
    SELECT MIN(salary) FROM employees
);

-- 案例4：查询最低工资大于50号部门的最低工资的部门id和其最低工资
-- 4.1. 查询50号部门的最低工资
SELECT MIN(salary) FROM employees
WHERE department_id = 50;

-- 4.2. 查询每个部门的最低工资
SELECT MIN(salary), department_id FROM employees
GROUP BY department_id;

-- 4.3. 在4.2基础上筛选满足MIN(salary) > 4.1 的结果
SELECT MIN(salary), department_id FROM employees
GROUP BY department_id
HAVING MIN(salary) > (
    SELECT MIN(salary) FROM employees
    WHERE department_id = 50
);


-- 非法使用标量子查询，标量子查询的结果不是一行一列
SELECT MIN(salary), department_id FROM employees
GROUP BY department_id
HAVING MIN(salary) > (
    SELECT salary FROM employees
    WHERE department_id = 50
);


-- 2. 列子查询（多行子查询）

-- 案例1：返回location_id是1400或1700的部门中的所有员工姓名
-- 1.1. 查询location_id是1400或1700的部门编号
SELECT DISTINCT department_id FROM departments
WHERE location_id IN (1400, 1700);

-- 1.2. 查询员工姓名，满足 department_id 是 1.1 列表中的某一个
SELECT last_name, department_id FROM employees
WHERE department_id IN (
    SELECT DISTINCT department_id FROM departments
    WHERE location_id IN (1400, 1700)
);

-- 案例2：返回其他工种中比job_id为'IT_PROG'的部门任一工资低的员工的employee_id, last_name, job_id, salary
-- 2.1. 查询job_id = 'IT_PROG'部门工资
SELECT DISTINCT salary FROM employees
WHERE job_id = 'IT_PROG';

-- 2.2. 查询其他工种中 salary < 2.1 的任一工资的员工
SELECT employee_id, last_name, job_id, salary FROM employees
WHERE salary < ANY(
    SELECT DISTINCT salary FROM employees
    WHERE job_id = 'IT_PROG'
) AND job_id <> 'IT_PROG';

SELECT employee_id, last_name, job_id, salary FROM employees
WHERE salary < (
    SELECT MAX(salary) FROM employees
    WHERE job_id = 'IT_PROG'
) AND job_id <> 'IT_PROG';

-- 案例3：返回其他工种中比job_id为'IT_PROG'部门所有工资都低的员工的employee_id, last_name, job_id, salary
SELECT employee_id, last_name, job_id, salary FROM employees
WHERE salary < ALL(
    SELECT DISTINCT salary FROM employees
    WHERE job_id = 'IT_PROG'
) AND job_id <> 'IT_PROG';

SELECT employee_id, last_name, job_id, salary FROM employees
WHERE salary < (
    SELECT MIN(salary) FROM employees
    WHERE job_id = 'IT_PROG'
) AND job_id <> 'IT_PROG';


-- 3. 行子查询（结果集一行多列或多行多列）
-- 案例1：查询员工编号最小并且工资最高的员工信息
-- 1.1. 查询最小的员工编号
SELECT MIN(employee_id) FROM employees;

-- 1.2. 查询工资最高的员工工资
SELECT MAX(salary) FROM employees;

-- 1.3. 查询员工信息
SELECT * FROM employees
WHERE employee_id = (
    SELECT MIN(employee_id) FROM employees
) AND salary = (
    SELECT MAX(salary) FROM employees
);

-- 1.4. 行子查询
SELECT * FROM employees
WHERE (employee_id, salary) = (
    SELECT MIN(employee_id), MAX(salary) FROM employees
);


-- 二：SELECT 后面

-- 1. 标量子查询
-- 案例1：查询每个部门的员工个数
SELECT d.*, (
    SELECT COUNT(*) FROM employees e
    WHERE e.department_id = d.department_id
) 员工个数 FROM departments d;

-- 案例2：查询员工号=102的部门名
SELECT (
    SELECT d.department_name, FROM departments d
    INNER JOIN employees e ON d.department_id = e.department_id
    WHERE e.employee_id = 102
) 部门名;


-- 三：FROM 后面

-- 1. 表子查询
-- 案例：查询每个部门的平均工资的工资等级
SELECT e.department_id, AVG(e.salary) ag, (
    SELECT jg.grade_level FROM job_grades jg
    WHERE AVG(salary) BETWEEN jg.lowest_sal AND jg.highest_sal
) 工资等级 FROM employees e
GROUP BY department_id;

SELECT ag_dep.*, jg.grade_level 工资等级 FROM (
    SELECT e.department_id, AVG(e.salary) ag FROM employees e
    GROUP BY e.department_id
) ag_dep
INNER JOIN job_grades jg ON ag_dep.ag BETWEEN jg.lowest_sal AND jg.highest_sal;


-- 四：EXISTS 后面（相关子查询）

-- 1. 表子查询
-- 案例1：查询有员工的部门名
SELECT d.department_id, d.department_name FROM departments d
WHERE EXISTS(
    SELECT 1 FROM employees e
    WHERE d.department_id = e.department_id
);

-- IN
SELECT d.department_id, d.department_name FROM departments d
WHERE d.department_id IN (
    SELECT e.department_id FROM employees e
);

-- 案例2：查询没有女朋友的男生信息
SELECT b.* FROM boys b
WHERE NOT EXISTS(
    SELECT 1 FROM beauty g
    WHERE b.id = g.boyfriend_id
);

-- IN
SELECT b.* FROM boys b
WHERE b.id NOT IN(
    SELECT g.boyfriend_id FROM beauty g
);



-- 1. 查询和Zlotkey相同部门的last_name, salary
SELECT e.last_name, e.salary FROM employees e
WHERE e.department_id = (
    SELECT department_id FROM employees
    WHERE last_name = 'Zlotkey'
);

-- 2. 查询工资比公司平均工资高的员工的employee_id, last_name, salary
SELECT e.employee_id, e.last_name, e.salary FROM employees e
WHERE e.salary > (
    SELECT AVG(salary) FROM employees
);

-- 3. 查询各部门中工资比本部门平均工资高的员工的employee_id, last_name, salary
SELECT AVG(salary), department_id FROM employees
GROUP BY department_id;

SELECT e.employee_id, e.last_name, e.salary, e.department_id FROM employees e
INNER JOIN (
    SELECT AVG(salary) ag, department_id FROM employees
    GROUP BY department_id
) ag_dep ON e.department_id = ag_dep.department_id
WHERE e.salary > ag_dep.ag;

-- 4. 查询和姓名中包含字母u的员工在相同部门的员工的employee_id, last_name
SELECT e.employee_id, e.last_name FROM employees e
WHERE e.department_id IN (
    SELECT DISTINCT department_id FROM employees WHERE last_name LIKE '%u%'
);

-- 5. 查询在部门的location_id为1700的部门工作的员工的employee_id
SELECT e.employee_id, e.last_name FROM employees e
WHERE e.department_id IN (
    SELECT DISTINCT department_id FROM departments WHERE location_id = 1700
);

-- 6. 查询管理者是K_ing的员工last_name, salary
SELECT e.employee_id, e.manager_id, e.last_name, e.salary FROM employees e
WHERE e.manager_id IN (
    SELECT employee_id FROM employees
    WHERE last_name = 'K_ing' AND manager_id IS NULL
);

-- 7. 查询工资最高的员工的last_name, 要求first_name和last_name显示为一列，列明为姓.名
SELECT e.*, CONCAT(e.first_name, '.', e.last_name) '姓.名' FROM employees e
WHERE e.salary = (
    SELECT MAX(salary) FROM employees
);

-- 8. 查询工资最低的员工信息
SELECT * FROM employees
WHERE salary = (
    SELECT MIN(salary) FROM employees
);

-- 9. 查询平均工资最低的部门信息
SELECT * FROM departments
WHERE department_id = (
    SELECT department_id FROM employees
    GROUP BY department_id
    ORDER BY AVG(salary)
    LIMIT 1
);

-- 10. 查询平均工资最低的部门信息和该部门的平均工资
SELECT d.*, (
    SELECT AVG(salary) FROM employees e
    WHERE e.department_id = d.department_id
) 平均工资 FROM departments d
WHERE d.department_id = (
    SELECT department_id FROM employees
    GROUP BY department_id
    ORDER BY AVG(salary)
    LIMIT 1
);

SELECT d.*, ag_dep.ag 平均工资 FROM departments d
INNER JOIN (
    SELECT department_id, AVG(salary) ag FROM employees
    GROUP BY department_id
    ORDER BY ag
    LIMIT 1
) ag_dep ON d.department_id = ag_dep.department_id;

-- 11. 查询平均工资最高的 job 信息
SELECT * FROM jobs
WHERE job_id = (
    SELECT job_id FROM employees
    GROUP BY job_id
    ORDER BY AVG(salary) DESC
    LIMIT 1
);

-- 12. 查询平均工资高于公司平均工资的部门有哪些
SELECT * FROM departments
WHERE department_id IN (
    SELECT department_id FROM employees
    GROUP BY department_id
    HAVING AVG(salary) > (
        SELECT AVG(salary) FROM employees
    )
);

-- 13. 查询公司中所有 manager 的详细信息
SELECT * FROM employees
WHERE employee_id IN (
    SELECT manager_id FROM employees
);

-- 14. 查询各个部门中 最高工资中最低的那个部门的最低工资
SELECT MIN(salary) FROM employees
WHERE department_id = (
    SELECT department_id FROM employees
    GROUP BY department_id
    ORDER BY MAX(salary)
    LIMIT 1
);

-- 15. 查询平均工资最高的部门的 manager 的相信信息
SELECT * FROM employees
WHERE employee_id IN (
    SELECT DISTINCT manager_id FROM employees
    WHERE department_id = (
        SELECT department_id FROM employees
        GROUP BY department_id
        ORDER BY MAX(salary) DESC
        LIMIT 1
    )
);
```

## 5.8. 分页查询

```sql
-- 进阶8：分页查询
/*
	应用场景：当要显示的数据，一页显示不全，需要分页提交sql请求
	语法：
		SELECT
			查询列表
		FROM
			表名
		LIMIT [offset,] size;
		
		offset:	要显示条目的起始索引（起始索引从0开始）
		size:	要显示的条目个数
		
	特点：
		1. LIMIT 语句放在查询语句的最后
		2. 公式
			要显示的页数 page, 每页的条目数 size
			
			SELECT 查询列表 FROM 表
			LIMIT (page - 1) * size, size;
*/

-- 案例1：查询前5条员工信息
SELECT * FROM employees
LIMIT 5;

-- 案例2：查询第11条到第25条
SELECT * FROM employees
LIMIT 10, 15;

-- 案例3：查询有奖金的员工信息，显示工资较高的前10名
SELECT * FROM employees
WHERE commission_pct IS NOT NULL
ORDER BY salary DESC
LIMIT 10;
```

# 6. DML语言的学习

- 数据操作语言

## 6.1. 数据的增删改

```sql
-- DML 语言
/*
	数据操作语言：
		插入：INSERT
		修改：UPDATE
		删除：DELETE
*/

-- 一、插入语句
/*
	方式一：
		语法：
			INSERT INTO 表名(列名,...)
			VALUES(值1,...);
			
	方式二：
		语法：
			INSERT INTO 表名
			SET 列名 = 值,...;
			
	两种方式 pk：
		1. 方式一支持插入多行，方式二不支持
		2. 方式一支持子查询，方式二不支持
*/

SELECT * FROM beauty;

-- 1. 插入的值的类型要与列的类型一致或兼容
INSERT INTO beauty(id, beautyName, sex, borndate, phone, photo, boyfriend_id)
VALUES(13, '唐艺昕', '女', '1990-4-23', '18988888888', NULL, 2);

-- 2. 不可以为NULL的列必须插入值，可以为NULL的列如何插入值？
-- 方式一：插入NULL
INSERT INTO beauty(id, beautyName, sex, borndate, phone, photo, boyfriend_id)
VALUES(13, '唐艺昕', '女', '1990-4-23', '18988888888', NULL, 2);
-- 方式二：不写该列
INSERT INTO beauty(id, beautyName, sex, borndate, phone, boyfriend_id)
VALUES(14, '金星', '女', '1990-4-23', '18988888888', 9);

-- 3. 列的顺序是否可以调换
INSERT INTO beauty(beautyName, sex, id, phone)
VALUES('蒋欣', '女', 16, '110');

-- 4. 列数和值的个数必须一致
INSERT INTO beauty(beautyName, sex, id, phone)
VALUES('关晓彤', '女', 15, '110');

-- 5. 可以省略列名，默认所有列，而且列的顺序和表中列的顺序一致
INSERT INTO beauty
VALUES(18, '张飞', '男', NULL, 119, NULL, NULL);

-- 6. 插入子查询
INSERT INTO beauty(id, beautyName, phone)
SELECT 20, '宋茜', '11809866';


-- 方式二
INSERT INTO beauty
SET id = 19, beautyName = '刘涛', phone = '999';


-- 二、修改语句
/*
	1. 修改单表的记录
		语法：
			UPDATE 表名
			SET 列名 = 新值,...
			WHERE 筛选条件;
	2. 修改多表的记录[补充]
		语法：
			sql92：
				UPDATE 表1 别名, 表2 别名
				SET 列名= 新值,...
				WHERE 连接条件 AND 筛选条件;
			sql99：
				UPDATE 表1 别名
				INNER|LEFT|RIGHT JOIN 表2 别名 ON 连接条件
				SET 列 = 新值,...
				WHERE 筛选条件;
*/

-- 1. 修改单表的记录
-- 案例1：修改beauty表中姓唐的女生的电话为13899888899
UPDATE beauty
SET phone = '13899888899'
WHERE beautyName LIKE '唐%';

-- 案例2：修改boys表中id=2的名称为张飞，魅力值为10
UPDATE boys
SET boyName = '张飞', userCP = 10
WHERE id = 2;

-- 2. 修改多表的记录
-- 案例1：修改张无忌的女朋友的手机号为114
UPDATE boys b
INNER JOIN beauty g ON b.id = g.boyfriend_id
SET g.phone = 114
WHERE b.boyName = '张无忌';

-- 案例2：修改没有男朋友的女生的男朋友编号都为2
UPDATE boys b
RIGHT JOIN beauty g ON b.id = g.boyfriend_id
SET g.boyfriend_id = 2
WHERE b.id IS NULL;

-- 查询没有男朋友的女生的男朋友信息
SELECT * FROM boys b
RIGHT JOIN beauty g ON b.id = g.boyfriend_id
WHERE b.id IS NULL;


-- 三、删除语句
/*
	方式一：DELETE
		1. 单表的删除
			语法：
				DELETE FROM 表名
				WHERE 筛选条件;
		2. 多表的删除[补充]
			语法：
				sql92：
					DELETE 表1的别名, 表2的别名 FROM 表1 别名, 表2 别名
					WHERE 连接条件 AND 筛选条件;
				sql99：
					DELETE 表1的别名, 表2的别名 FROM 表1 别名
					INNER|LEFT|RIGHT JOIN 表2 别名 ON 连接条件
					WHERE 筛选条件;
		
	方式二：TRUNCATE
		语法：
			TRUNCATE TABLE 表名;
			
	DELETE, TRUNCATE pk
		1. DELETE 可以加WHERE条件，TRUNCATE不能加
		2. TRUNCATE 删除，效率高
		3. 加入要删除的表中有自增长列，如果用DELETE删除后，再插入数据，自增长的列的值从断开开始，
			而TRUNCATE删除后，再插入数据，自增长列的值从1开始
		4. DELETE 删除有返回值，TRUNCATE 删除无返回值
		5. DELETE 删除可以回滚，TRUNCATE 删除不能回滚
*/

-- 方式一：DELETE 语句

-- 1. 单表的删除
-- 案例1：删除手机号以9结尾的女生信息
DELETE FROM beauty
WHERE phone LIKE '%9';

-- 2. 多表的删除
-- 案例1：删除张无忌的女朋友的信息
DELETE g FROM beauty g
INNER JOIN boys b ON b.id = g.boyfriend_id
WHERE b.boyName = '张无忌';

-- 案例2：删除黄晓明的信息以及他女朋友的信息
DELETE g, b FROM beauty g
INNER JOIN boys b ON b.id = g.boyfriend_id
WHERE b.boyName = '黄晓明';

-- 方式二：TRUNCATE 语句
-- 案例1：将魅力值>100的男生删除
TRUNCATE TABLE boys;
```



# 7. DDL语言的学习

# 8. TCL语言的学习

# 9. 视图的讲解

# 10. 存储过程和函数

# 11. 流程控制结构