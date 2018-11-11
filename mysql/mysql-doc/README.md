# 1.  为什么要学习数据库

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

# 5. DQL语言

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

## 5.9. 联合查询

```sql
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
```

## 5.10. 查询总结

```sql
-- 进阶10：查询总结
/*
	语法：
		SELECT 查询列表					8
		FROM 表1 别名					 1
		INNER|LEFT|RIGHT JOIN 表2	  2
		ON 连接条件						3
		WHERE 筛选条件					4
		GROUP BY 分组列表				5
		HAVING 筛选					 6
		ORDER BY 排序列表				7
		LIMIT offset, size;			   9
		
	执行顺序：FROM, JOIN, ON, WHERE, GROUP BY, HAVING, SELECT, ORDER BY, LIMIT
*/
```

# 6. DML语言

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
		3. 加入要删除的表中有自增长列，如果用DELETE删除后，再插入数据，自增长的列的值从断点开始，
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

- 数据定义语言

## 7.1. 库和表的管理

```sql
-- DDL 语言
/*
	数据定义语言；库和表的管理
	
	分类：
		一、库的管理
			创建、修改、删除
		二、表的管理
			创建、修改、删除
			
	创建：CREATE
	修改：ALTER
	删除：DROP
*/

-- 一、库的管理
-- 1. 库的创建
/*
	语法：
		CREATE DATABASE 库名;
*/

-- 案例：创建库 books
CREATE DATABASE books;

-- 2. 库的修改（不安全，已过时；无法修改数据库名）
RENAME DATABASE books TO book;

-- 2.1. 修改库的字符集
ALTER DATABASE books CHARACTER SET utf8;

-- 3. 库的删除
DROP DATABASE IF EXISTS books;

-- 二、表的管理
-- 1. 表的创建
/*
	CREATE TABLE 表名 (
		列名 列的类型[(长度) 约束],
		列名 列的类型[(长度) 约束],
		列名 列的类型[(长度) 约束],
		...
		列名 列的类型[(长度) 约束]
	);
*/

-- 案例：创建表book
CREATE TABLE book (
    id INT,
    b_name VARCHAR(20),
    price DOUBLE,
    author INT,
    publish_date DATETIME
);

SHOW FULL COLUMNS FROM book;

-- 案例：创建表author
CREATE TABLE author (
    id INT,
    au_name VARCHAR(20),
    nation VARCHAR(10)
);

SHOW FULL COLUMNS FROM author;

-- 2. 表的修改
/*
	ALTER TABLE 表名 ADD|DROP|MODIFY|CHANGE COLUMN 列名 [新列名, 列类型, 约束];
*/
-- 2.1. 修改列名
ALTER TABLE book CHANGE COLUMN publish_date pub_date DATETIME;

-- 2.2. 修改列的类型或约束
ALTER TABLE book MODIFY COLUMN pub_date TIMESTAMP;

-- 2.3. 添加新列
-- ALTER TABLE 表名 ADD COLUMN 列名 [新列名, 列类型, 约束] [FIRST|AFTER 字段名];
ALTER TABLE author ADD COLUMN annal DOUBLE;

-- 2.4. 删除列
ALTER TABLE author DROP COLUMN annal;

-- 2.5. 修改表名
ALTER TABLE author RENAME TO book_author;

-- 3. 表的删除
DROP TABLE IF EXISTS book_author;


-- 通用的写法：
DROP DATABASE IF EXISTS 旧库名;
CREATE DATABASE 库名;

DROP TABLE IF EXISTS 旧表名;
CREATE TABLE 表名();

-- 4. 表的复制
INSERT INTO author(id, au_name, nation) VALUES
(1, '村上春树', '日本'),
(2, '莫言', '中国'),
(3, '冯唐', '中国'),
(4, '金庸', '中国');

-- 4.1. 复制表的结构
CREATE TABLE copy01_author LIKE author;

-- 4.2. 复制表的结构、数据
CREATE TABLE copy02_author
SELECT * FROM author;

-- 4.2. 复制表的结构、部分数据；复制部分数据（添加筛选条件）
CREATE TABLE copy03_author
SELECT * FROM author
WHERE nation = '中国';

-- 4.3. 复制表的部分结构
CREATE TABLE copy04_author
SELECT id, au_name FROM author
WHERE 0;
```

## 7.2. 常见的数据类型

```sql
-- 常见的数据类型
/*
	数值型：
		整型
		小数：
			浮点数
			定点数
	字符型：
		较短的文本：CHAR, VARCHAR
		较长的文本：TEXT, BLOB（较长的二进制数据）
	日期型：
		DATETIME
*/

-- 一、整型
/*
	分类：
		TINYINT,	SMALLINT,	MEDIUMINT,	INT/INTEGER,	BIGINT
		1字节		2字节		3字节		4字节		8字节
	特点：
		1. 如果不设置无符号还是有符号，默认是有符号；如果要设置无符号，需要添加 UNSIGNED 关键字
		2. 如果插入的数值超出了整型的范围，会报 Out of range value ，默认插入的是临界值
		3. 如果不设置长度，会有默认的长度；长度代表了显示的最大宽度，如果不够会用0左填充，但必须搭配 ZEROFILL 使用，并且默认变为无符号整型
*/

USE test;
-- 1. 如何设置无符号和有符号
DROP TABLE IF EXISTS tab_int;
CREATE TABLE tab_int (
	t1 INT,
	t2 INT UNSIGNED
);
SHOW FULL COLUMNS FROM tab_int;

INSERT INTO tab_int(t1, t2) VALUES(-1234567, -1234567);
INSERT INTO tab_int(t1, t2) VALUES(-12345671111111111, -1234567);
INSERT INTO tab_int(t1, t2) VALUES(12345671111111111, 12345671111111);

-- 二、小数
/*
	分类：
		1. 浮点型
			FLOAT(M, D)
			DOUBLE(M, D)
		2. 定点型
			DEC(M, D)
			DECIMAL(M, D) 同 DEC(M, D)
	特点：
		1. M：整数部位+小数部位的个数，D：小数部位的个数，如果超过范围，则插入临界值
		2. 如果超出范围，会报 Out of range value 异常，并且插入临界值
		3. M和D可以省略，如果是DECIMAL，则M默认为10，D为0；如果是FLOAT和DOUBLE，则会根据具插入的数值的精度来决定精度
		4. 定点型的精确度较高，如果要求插入数值的精度较高如货币运算则考虑使用
		
	原则：
		所选择的类型越简单越好；能保存数值的类型越小越好
*/

-- 测试M和D
DROP TABLE IF EXISTS tab_float;
CREATE TABLE tab_float (
	f1 FLOAT,
	f2 DOUBLE,
	f3 DEC,
	f4 DECIMAL
);
SHOW FULL COLUMNS FROM tab_float;

INSERT INTO tab_float(f1, f2, f3, f4) VALUES(123.45, 123.45, 123.45, 123.45);
INSERT INTO tab_float(f1, f2, f3, f4) VALUES(123.45, 123.45, 123.45, 123.456);
INSERT INTO tab_float(f1, f2, f3, f4) VALUES(123.45, 123.45, 123.45, 123.4);
INSERT INTO tab_float(f1, f2, f3, f4) VALUES(1234.45, 123.45, 123.45, 123.4);

-- 三、字符型
/*
	分类：
		CHAR	固定长度的字符，写法为CHAR(M)，最大长度不能超过M，M可以省略，默认为1
		VARCHAR	可变长度的字符，写法为VARCHAR(M)，最大长度不能超过M，M不可以省略
		BINARY, VARBINARY, ENUM, SET, TEXT, BLOB
*/

-- 四、日期型
/*
	分类：
		YEAR		年			1字节
		DATE		日期			3字节
		TIME		时间			3字节
		DATETIME	日期+时间		8字节
		TIMESTAMP	日期+时间		4字节	比较容易受时区、语法模式、版本的影响，更能反映当前时区的真实时间
*/
```

## 7.3. 常见约束

```sql
-- 常见约束
/*
	含义：一种限制，用于限制表中的数据，为了保证表中的数据的准确和可靠性
	分类：
		六大约束：
			NOT NULL	非空，用于保证该字段的值不能为空。比如：姓名，学号等
			DEFAULT		默认，用于保证该字段有默认值。比如：性别
			PRIMARY KEY	主键，用于保证该字段的值具有唯一性，并且非空。比如：学号、员工编号等
			UNIQUE		唯一，用于保证该字段的值具有唯一性，但是可以为NULL。比如：座位号
			CHECK		检查约束[mysql中不支持]。比如：年龄、性别
			FOREIGN KEY	外键，用于限制两个表的性关系，保证该字段的值必须来自于主表的关联列的值。
					在从表添加外键约束，用于引用主表中某列的值。比如：员工表的部门编号
					
	约束的时机：
		1. 创建表时
			1.1. 添加列级约束
				语法：直接在字段名和类型后面追加约束类型即可。只支持：非空、默认、主键、唯一
			1.2. 添加表级约束
				语法：在所有字段的最下面。添加 [CONSTRAINT 约束名] 约束类型（字段名）
		2. 修改表时
			2.1. 添加列约束
				语法：ALTER TABLE 表名 MODIFY COLUMN 字段名 字段类型 新约束;
			2.2. 添加表级约束
				语法：ALTER TABLE 表名 ADD [CONSTRAINT 约束名] 约束类型（字段名） [外键的引用];
		3. 删除表时
		
	约束的添加分类：
		2. 列级约束：六大约束语法上都支持，外键约束没有效果
		2. 表级约束：除了非空，默认，其他的都支持
		
	主键，唯一 pk：
					主键		唯一
		保证唯一性		支持		支持
		是否允许为空		不允许		允许
		一个表中可以有多少个	至少有一个	可以有多个
		是否允许组合		允许，不推荐	允许，不推荐
	外键特点：
		1. 要求在从表设置外键关联
		2. 从表的挖建列的类型和主表的关联列的类型一致或兼容，名称无要求
		3. 主表的管理案例额必须是一个key（一般主键或唯一）
		4. 插入数据时，先插入主表，再插入从表；删除数据时，先删除从表，再删除主表
*/

CREATE DATABASE IF NOT EXISTS students;
USE students;

-- 一、创建表时添加约束
-- 1. 添加列级约束
CREATE TABLE stu_info (
	id INT PRIMARY KEY,					-- 主键
	stu_name VARCHAR(20) NOT NULL,				-- 非空
	gender CHAR(1), 					-- 检查，无效
	seat INT UNIQUE,					-- 唯一
	age INT DEFAULT 18,					-- 默认
	major_id INT						-- 外键，无效
);

CREATE TABLE major (
	id INT PRIMARY KEY,
	major_name VARCHAR(20) NOT NULL
);

-- 查看stu_info中的所有索引，包括主键、外键、唯一
DESC stu_info;
SHOW INDEX FROM stu_info;


-- 2. 添加表级约束
DROP TABLE IF EXISTS stu_info;
CREATE TABLE stu_info (
	id INT,
	stu_name VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	major_id INT,
	
	CONSTRAINT pk PRIMARY KEY (id),							-- 主键
	CONSTRAINT ug UNIQUE (seat),							-- 唯一
	CONSTRAINT fk_stu_info_major FOREIGN KEY (major_id) REFERENCES major(id)	-- 外键
);

DROP TABLE IF EXISTS stu_info;
CREATE TABLE stu_info (
	id INT,
	stu_name VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	major_id INT,
	
	PRIMARY KEY (id),								-- 主键
	UNIQUE (seat),									-- 唯一
	FOREIGN KEY (major_id) REFERENCES major(id)					-- 外键
);


-- 通用的写法：
DROP TABLE IF EXISTS stu_info;
CREATE TABLE stu_info (
	id INT PRIMARY KEY,								-- 主键
	stu_name VARCHAR(20) NOT NULL,							-- 非空
	gender CHAR(1),
	seat INT DEFAULT 18,								-- 默认
	age INT UNIQUE,									-- 唯一
	major_id INT,
	CONSTRAINT fk_stu_info_major FOREIGN KEY (major_id) REFERENCES major(id)	-- 外键
);


-- 二、修改表时添加约束
DROP TABLE IF EXISTS stu_info;
CREATE TABLE stu_info (
	id INT,
	stu_name VARCHAR(20),
	gender CHAR(1),
	seat INT,
	age INT,
	major_id INT
);
DESC stu_info;
SHOW INDEX FROM stu_info;
-- 1. 添加非空约束
ALTER TABLE stu_info MODIFY COLUMN stu_name VARCHAR(20) NOT NULL;

-- 2. 添加默认约束
ALTER TABLE stu_info MODIFY COLUMN age INT DEFAULT 18;

-- 3. 添加主键约束
-- 3.1. 列级约束
ALTER TABLE stu_info MODIFY COLUMN id INT PRIMARY KEY;
-- 3.2. 表级约束
ALTER TABLE stu_info ADD PRIMARY KEY(id);

-- 4. 添加唯一
-- 4.1. 列级约束
ALTER TABLE stu_info MODIFY COLUMN seat INT UNIQUE;
-- 4.2. 表级约束
ALTER TABLE stu_info ADD UNIQUE (seat);

-- 5. 添加外键
ALTER TABLE stu_info ADD CONSTRAINT fk_stu_info_major FOREIGN KEY (major_id) REFERENCES major(id);


-- 三、修改表时删除约束
-- 1. 删除非空约束
ALTER TABLE stu_info MODIFY COLUMN stu_name VARCHAR(20) NULL;

-- 2. 删除默认约束
ALTER TABLE stu_info MODIFY COLUMN age INT;

-- 3. 删除主键
ALTER TABLE stu_info DROP PRIMARY KEY;

-- 4. 删除唯一
ALTER TABLE stu_info DROP INDEX seat;

-- 5. 删除外键约束
ALTER TABLE stu_info DROP FOREIGN KEY fk_stu_info_major;
-- 6. 删除外键
ALTER TABLE stu_info DROP major_id;

-- 查看数据库表创建的sql语句
SHOW CREATE TABLE stu_info;
```

## 7.4. 标识列

```sql
-- 标识列
/*
	又称为自增长列
	含义：可以不用手动的插入值，系统提供默认的序列值
	
	特点：
		1. 标识列必须和主键搭配吗？不一定，但要求是一个key
		2. 一个表可以有几个标识列？最多一个
		3. 标识列的类型只能是数值型
		4. 标识列可以通过 SET AUTO_INCREMENT = 3; 设置步长；可以通过手动插入值，设置起始值
*/

-- 一、创建表时设置标识列
DROP TABLE IF EXISTS tab_identity;
CREATE TABLE tab_identity (
	id INT PRIMARY KEY,
	id_name VARCHAR(20),
	seat INT
);

INSERT INTO tab_identity VALUES(NULL, 'john');
INSERT INTO tab_identity(id_name) VALUES('lucy');

SHOW VARIABLES LIKE '%auto_increment%';

SHOW CREATE TABLE tab_identity;

-- 二、修改表时设置标识列
ALTER TABLE tab_identity MODIFY COLUMN id INT AUTO_INCREMENT;

-- 三、修改表时删除标识列
ALTER TABLE tab_identity MODIFY COLUMN id INT;
```

# 8. TCL语言

## 8.1. 事务控制

```sql
-- TCL：事务控制语言
/*
	Transaction Control Language 事务控制语言
	
	事务：
		一个或一组sql语句组成一个执行单元，这个执行单元要么全部执行，要么全部不执行。
		
	案例：转账
		 张三丰	1000
		 郭襄	1000
		 
		 UPDATE 表 SET 张三丰的余额 = 500 WHERE name = '张三丰';
		 意外
		 UPDATE 表 SET 郭襄的余额 = 1500 WHERE name = '郭襄';
		 
	事务的特性（ACID属性）：
		1. 原子性（Atomicity）
			原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
 		2. 一致性（Consistecy）
			事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
		3. 隔离性（Isolation）
			事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
		4. 持久性（Durability）
			持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响。
			
	事务的创建
		隐式事务：事务没有明显的开启和结束的标记；比如：INSERT, UPDATE, DELETE语句
		显式事务：事务具有明显的开启和结束的标记；前提：必须设置自动提交功能为禁用 SET autocommit = 0;
			步骤1：开启事务
				SET autocommit = 0;
				BEGIN;或START TRANSACTION;
			步骤2：编写事务中的sql语句（SELECT, INSERT, UPDATE, DELETE）
				语句1;
				语句2;
				...
			步骤3：结束事务
				COMMIT; 提交事务
				ROLLBACK; 回滚事务
				
	MYSQL 事务处理主要有两种方法：
		1. 用 BEGIN, ROLLBACK, COMMIT来实现
		2. 直接用 SET 来改变 mysql 的自动提交模式：
			SET AUTOCOMMIT=0 禁止自动提交
			SET AUTOCOMMIT=1 开启自动提交
			
	并发事务：
		1. 事务的并发问题是如何发生的？
			多个事务同事操作同一个数据库的相同数据时
		2. 并发问题都有哪些？
			2.1. 脏读：		一个事务读取了其他事务还没提交的数据，读到的是其他事务"更新"的数据
			2.2. 不可重复读：	一个事务多次读取，结果不一样
			2.3. 幻读：		一个事务读取了其他事务还没提交的数据，读到的是其他事务"插入"的数据
		3. 如何解决并发问题？
			通过设置隔离级别来解决并发问题
		4. 隔离级别有哪些？解决了哪些并发问题？
								脏读	不可重复读	幻读
			1. READ UNCOMMITTED	读未提交	×	×		×
			2. READ COMMITTED	读已提交	√	×		×	ORACLE 默认
			3. REPEATABLE READ	可重复读	√	√		×	MYSQL 默认
			4. SERIALIZABLE		串行化		√	√		√
*/

-- 查看数据库存储引擎
SHOW ENGINES;

SHOW VARIABLES LIKE 'autocommit';
SET autocommit = 1;

-- 演示事务的使用步骤
USE test;
DROP TABLE IF EXISTS account;
CREATE TABLE account (
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20),
	balance DOUBLE
);

INSERT INTO account(username, balance) VALUES
('张无忌', 1000),
('赵敏', 1000);

-- 1. 开启事务
BEGIN;
-- 2. 编写一组事务的语句
UPDATE account SET balance = 500 WHERE username = '张无忌';
UPDATE account SET balance = 1500 WHERE username = '赵敏';
-- 3. 结束事务
COMMIT; -- 提交事务
ROLLBACK; -- 回滚事务

SELECT * FROM account;
```

# 9. 视图

## 9.1. 视图

```sql
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
```

# 10. 其他

## 10.1. 视图

```sql
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
```

## 10.2. 变量

```sql
-- 变量
/*
	分类：
		1. 系统变量：
			1.1. 全局变量
				作用域：服务器每次启动将为所有的全局变量赋初始值，针对于所有的会话（连接）有效，但不能跨重启
			1.2. 会话变量
				作用域：仅仅针对于当前会话（连接）有效
		2. 自定义变量
			2.1. 用户变量
				作用域：仅仅针对于当前会话（连接）有效。应用在任何地方，也就是BEGIN END里面或BEGIN END的外面
			2.2. 局部变量
				作用域：仅仅在定义它的BEGIN END中有效。应用在BEGIN END中的第一句话
				
			用户变量对比局部变量：
						作用域			定义和使用的位置			语法
				用户变量	当前会话		会话中的任何地方			需要加@符号，不用限定类型
				局部变量	BEGIN END中		只能在BEGIN END中，且为第一句话		一般不用加@符号，需要限定类型
*/

-- 一、系统变量
/*
	说明：变量由系统提供，不是用户定义，属于服务器层面
	语法：
		1. 查看所有的系统变量
			SHOW GLOBAL|[SESSION] VARIABLES;
		2. 查看满足条件的部分系统变量
			SHOW GLOBAL|[SESSION] VARIABLES LIKE '%char%';
		3. 查看指定的某个系统变量的值
			SELECT @@GLOBAL|[SESSION].系统变量名;
		4. 为某个系统变量赋值
			方式一：SET GLOBAL|[SESSION] 系统变量名 = 值;
			方式二：SET @@GLOBAL|[SESSION].系统变量名 = 值;
	注意：如果是全局级别，则需要加GLOBAL；如果是会话级别，则需要加SESSION；如果什么都不写，则默认SESSION。
*/

-- 1. 全局变量
-- 1.1. 查看所有的全局变量
SHOW GLOBAL VARIABLES;

-- 1.2. 查看部分的全局变量
SHOW GLOBAL VARIABLES LIKE '%char%';

-- 1.3. 查看指定的全局变量的值
SELECT @@GLOBAL.autocommit;
SELECT @@tx_isolation;

-- 1.4. 为某个指定的全局变量赋值
SET GLOBAL autocommit = 0;
SET @@GLOBAL.autocommit = 1;

-- 2. 会话变量
-- 2.1. 查看所有的会话变量
SHOW SESSION VARIABLES;
SHOW VARIABLES;

-- 2.2. 查看部分的会话变量
SHOW VARIABLES LIKE '%char%';
SHOW SESSION VARIABLES LIKE '%char%';

-- 2.3. 查看指定的某个会话变量
SELECT @@tx_isolation;
SELECT @@SESSION.tx_isolation;

-- 2.4. 为某个会话变量赋值
SET SESSION tx_isolation = 'read-uncommitted';
SET @@SESSION.tx_isolation = 'REPEATABLE-READ';


-- 二、自定义变量
-- 1. 用户变量
/*
	说明：变量是用户自定义的，不是由系统的。
	使用步骤：
		声明, 赋值, 使用（查看、比较、运算等）
*/
-- 1.1. 声明并初始化。赋值的操作符：= 或 :=
/*
	SET @用户变量名 = 值;
	SET @用户变量名 := 值;
	SELECT @用户变量名 := 值;
*/

-- 1.2. 赋值（更新用户变量的值）
/*
	方式一：通过SET或SELECT
		SET @用户变量名 = 值;
		SET @用户变量名 := 值;
		SELECT @用户变量名 := 值;
	方式二：通过SELECT INTO
		SELECT 字段 INTO 用户变量名 FROM 表;
*/

-- 1.3. 查看
/*
	SELECT @用户变量名;
*/

-- 案例：
-- 1. 声明并初始化
SET @name = 'john';
SET @count = 1;

-- 2. 赋值
SET @name = 100;
SELECT COUNT(*) INTO @count FROM employees;

-- 3. 查看
SELECT @name;
SELECT @count;

-- 2. 局部变量
/*
	
*/
-- 2.1. 声明
/*
	DECLARE 变量名 类型;
	DECLARE 变量名 类型 DEFAULT 值;
*/

-- 2.2. 赋值
/*
	方式一：通过SET或SELECT
		SET 局部变量名 = 值;
		SET 局部变量名 := 值;
		SELECT @局部变量名 := 值;
	方式二：通过SELECT INTO
		SELECT 字段 INTO 局部变量名 FROM 表;
*/

-- 2.3. 使用
/*
	SELECT 局部变量名;
*/

-- 案例：声明两个变量并赋初始值，求和，并打印
-- 1. 用户变量
SET @m = 1;
SET @n = 2;
SET @sum = @m + @n;
SELECT @sum;

-- 2. 局部变量
DELIMITER $
BEGIN
DECLARE m INT DEFAULT 1;
DECLARE n INT DEFAULT 2;
DECLARE SUM INT;
SET SUM = m + n;
SELECT SUM;
END $
```

## 10.3. 存储过程

```sql
-- 存储过程和函数
/*
	存储过程和函数：类似于java中的方法
	好处：
		1. 提高代码的重用性
		2. 简化操作 
*/

-- 存储过程
/*
	含义：一组预先编译好的sql语句的集合，理解成批处理语句
	好处：
		1. 提高代码的重用性
		2. 简化操作
		3. 减少了编译次数并且减少了和数据库服务器的连接次数，提高了效率
*/

-- 一、创建存储过程
/*
	CREATE PROCEDURE 存储过程名（参数列表）
	BEGIN
		存储过程体（一组合法的sql语句）
	END
	
	注意：
		1. 参数列表包含三部分
				参数模式	参数名		参数类型
			举例：	IN		stu_name	VARCHAR(20)
			
			参数模式：
				IN	该参数可以作为输入，也就是该参数需要调用方传入值
				OUT	该参数可以作为输出，也就是该参数可以作为返回值
				INOUT	改参数既可以作为输入又可以作为输出，也就是该参数既需要传入值，又可以返回值
				
		2. 如果存储过程体仅仅只有一句话，BEGIN END可以省略。
			存储过程体中的每条sql语句的结尾要求必须加分号。
			存储过程的结尾可以使用 DELIMITER 重新设置
			语法：
				DELIMITER 结束标记
			案例：
				DELIMITER $
		
*/

-- 二、调用存储过程
/*
	CALL 存储过程名(实参列表);
*/

-- 1. 空参列表
-- 案例：插入到admin表中五条记录
SELECT * FROM admin;

DELIMITER $
CREATE PROCEDURE myp1()
BEGIN
	INSERT INTO admin(username, `password`) VALUES
	('join1', '0000'),
	('lily', '0000'),
	('rose', '0000'),
	('jack', '0000'),
	('tom', '0000');
END $;

-- 调用
CALL myp1;

-- 2. 创建带IN模式参数的存储过程
-- 案例1：创建存储过程，实现根据女生名，查询对应的男生信息
DELIMITER $
CREATE PROCEDURE myp2(IN beautyName VARCHAR(20))
BEGIN
	SELECT b.* FROM boys b
	RIGHT JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

-- 调用
CALL myp2('赵敏');

-- 案例2：创建存储过程，实现用户是否登录成功
DELIMITER $
CREATE PROCEDURE myp3(IN username VARCHAR(10), IN `password` VARCHAR(10))
BEGIN
	DECLARE result INT DEFAULT 0; 					-- 声明变量并初始化
	
	SELECT COUNT(*) INTO result					-- 赋值
	FROM admin a
	WHERE a.username = username AND a.password = `password`;
	
	SELECT IF(result > 0, '登录成功', '登录失败') AS 登录状态;	-- 使用
END $;

-- 调用
CALL myp3('张飞', '8888');
CALL myp3('john', '8888');

-- 3. 创建带OUT模式的存储过程
-- 案例1：根据女生名，返回对应的男生名
DELIMITER $
CREATE PROCEDURE myp4(IN beautyName VARCHAR(50), OUT boyName VARCHAR(20))
BEGIN
	SELECT b.boyName INTO boyName
	FROM boys b
	INNER JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

-- 调用
SET @bName='';	-- 不定义也可以
CALL myp4('小昭', @bName);
SELECT @bName;

-- 案例2：根据女生名，返回对应的男生名和男生魅力值
DELIMITER $
CREATE PROCEDURE myp5(IN beautyName VARCHAR(50), OUT boyName VARCHAR(20), OUT userCP INT)
BEGIN
	SELECT b.boyName, b.userCP INTO boyName, userCP
	FROM boys b
	INNER JOIN beauty g ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

CALL myp5('小昭', @bName, @userCP);
SELECT @bName, @userCP;

-- 4. 创建带INOUT模式的存储过程
-- 案例1：传入a和b两个值，最终a和b都翻倍并返回
DELIMITER $
CREATE PROCEDURE myp6(INOUT a INT, INOUT b INT)
BEGIN
	SET a = a * 2;
	SET b = b * 2;
END $;

-- 调用
SET @m = 10;
SET @n = 20;
CALL myp6(@m, @n);
SELECT @m, @n;


-- 三、删除存储过程
/*
	语法：DROP PROCEDURE 存储过程名;
*/
DROP PROCEDURE IF EXISTS myp1;

-- 四、查看存储过程的信息
SHOW CREATE PROCEDURE myp2;


-- 存储过程案例
-- 1. 创建存储过程实现传入用户名和密码，插入到acmin表中
DELIMITER $
CREATE PROCEDURE test_pro1(IN username VARCHAR(10), IN `password` VARCHAR(10))
BEGIN
	INSERT INTO admin(username, `password`) VALUES(username, `password`);
END $;

SET @username = 'jax';
SET @password = '0000';
CALL test_pro1(@username, @password);
CALL test_pro1('泰达米尔', '0000');
SELECT * FROM admin;

-- 2. 创建存储过程或函数实现传入女生编号，返回女生名和女生电话
DELIMITER $
CREATE PROCEDURE test_pro2(IN id INT, OUT beautyName VARCHAR(50), OUT phone VARCHAR(11))
BEGIN
	SELECT g.beautyName, g.phone INTO beautyName, phone
	FROM beauty g
	WHERE g.id = id;
END $;

CALL test_pro2('2', @beautyName, @phone);
SELECT @beautyName, @phone;

-- 3. 创建存储过程或函数实现传入两个女生生日，返回大小
DELIMITER $
CREATE PROCEDURE test_pro3(IN birth1 DATETIME, IN birth2 DATETIME, OUT result INT)
BEGIN
	SELECT DATEDIFF(birth1, birth2) INTO result;
END $;

CALL test_pro3('2018-11-01', NOW(), @result);
SELECT @result;

-- 4. 创建存储过程或者函数实现传入一个日期，格式化成xx年xx月xx日并返回
DELIMITER $
CREATE PROCEDURE test_pro4(IN myDate DATETIME, OUT strDate VARCHAR(32))
BEGIN
	SELECT DATE_FORMAT(myDate, '%y年%m月%d日') INTO strDate;
END $;

CALL test_pro4(NOW(), @str);
SELECT @str;

-- 5. 创建存储过程或函数实现传入女生名称，返回：女生 and 男生 格式的字符串
DELIMITER $
CREATE PROCEDURE test_pro5(IN beautyName VARCHAR(50), OUT result VARCHAR(32))
BEGIN
	SELECT CONCAT(IFNULL(g.beautyName, ''), ' AND ', IFNULL(b.boyName, 'NULL')) INTO result
	FROM beauty g
	LEFT JOIN boys b ON b.id = g.boyfriend_id
	WHERE g.beautyName = beautyName;
END $;

CALL test_pro5('柳岩', @result);
SELECT @result;

DROP PROCEDURE IF EXISTS test_pro5;

-- 6. 创建存储过程或函数，根据传入的条目数和起始索引，查询beauty表的记录
DELIMITER $
CREATE PROCEDURE test_pro6(IN size INT, IN startIndex INT)
BEGIN
	SELECT * FROM beauty
	LIMIT startIndex, size;
END $;

CALL test_pro6(3, 5);
```

## 10.4. 函数

```sql
-- 函数
 /*
	含义：一组预先编译好的sql语句的集合，理解成批处理语句
	好处：
		1. 提高代码的重用性
		2. 简化操作
		3. 减少了编译次数并且减少了和数据库服务器的连接次数，提高了效率
		
	区别：
		存储过程：	可以有0个返回，也可以有多个返回；适合做批量插入、批量更新
		函数：		有且仅有1个返回；适合做处理数据后返回一个结果
*/
-- 一、创建函数语法
 /*
	CREATE FUNCTION 函数名(参数列表) RETURNS 返回类型;
	BEGIN
		函数体
	END
	
	注意：
		1. 参数列表 	包含两部分：参数名 参数类型
		2. 函数体	肯定会有return语句，如果没有会报错；如果return语句没有放在函数体的最后也不报错，但不建议
		3. 函数体中仅有一句话，则可以省略BEGIN END;
		4. 使用DELIMITER语句设置结束标记。
*/
-- 案例演示
-- 1. 无参有返回
-- 案例1：返回公司的员工个数
USE myemployees;

DELIMITER $
CREATE FUNCTION myf1() RETURNS INT
BEGIN
	DECLARE c INT DEFAULT 0;	-- 定义局部变量
	SELECT COUNT(*) INTO c
	FROM employees;
	RETURN c;
END $;

SELECT myf1();

-- 2. 有参有返回
-- 案例1：根据员工名，返回它的工资
SELECT * FROM employees;
DELIMITER $
CREATE FUNCTION myf2(empName VARCHAR(20)) RETURNS DOUBLE
BEGIN
	SET @sal = 0;			-- 定义用户变量
	SELECT e.salary INTO @sal	-- 赋值
	FROM employees e
	WHERE e.last_name = empName;
	RETURN @sal;
END $;

SELECT myf2('Austin');

-- 案例3：根据部门名，返回该部门的平均工资
SELECT * FROM departments;
DELIMITER $
CREATE FUNCTION myf3(departmentName VARCHAR(3)) RETURNS DOUBLE
BEGIN
	DECLARE sal DOUBLE;		-- 定义局部变量
	SELECT AVG(salary) INTO sal
	FROM employees e
	INNER JOIN departments d ON d.department_id = e.department_id
	WHERE d.department_name = departmentName;
	RETURN sal;
END $;

SELECT myf3('Hum');

-- 二、查看函数
SHOW CREATE FUNCTION myf3;

-- 三、删除函数
DROP FUNCTION myf3;

-- 案例
-- 1. 创建函数，实现传入两个double，返回二者之和
DELIMITER $
CREATE FUNCTION myf4(num1 DOUBLE, num2 DOUBLE) RETURNS DOUBLE
BEGIN
	SET @sum = num1 + num2;
	RETURN @sum;
END $;

SELECT myf4(5.1, 5.2) result;
DROP FUNCTION myf4;
```

## 10.5. 流程控制结构

```sql
-- 流程控制结构
/*
	顺序结构：程序从上往下依次执行
	分支结构：程序从两条或多条路径中选择一条去执行
	循环结构：程序在满足一定条件的基础上，重复执行一段代码
*/

-- 一、分支结构
-- 1. IF函数
/*
	功能：实现简单的双分支
	语法：
		SELECT IF(表达式1, 表达式2, 表达式3)
	执行顺序：
		如果表达式1成立，则IF函数返回表达式2的值，否则返回表达式3的值
		
	应用：任何地方
*/

-- 2. CASE结构
/*
	情况1：类似于java中的switch语句，一般用于实现的等值判断
		CASE 变量|表达式|字段
			WHEN 要判断的值1 THEN 返回的值1或语句1
			WHEN 要判断的值 THEN 返回的值2或语句2
			...
			ELSE 要返回的值n或语句n;
		END CASE;
	情况2：类似于java中的多重IF语句，一般用于实现的区间判断
		CASE
			WHEN 要判断的条件1 THEN 返回的值1或语句1;
			WHEN 要判断的条件2 THEN 返回的值2或语句2;
			...
			ELSE 要返回的值n或语句n;
		END CASE;
		
	特点：
		1. 可以作为表达式，嵌套在其他语句中使用，可以放在任何地方，BEGIN END 中或 BEGIN END 的外面
		2. 可以作为独立的语句去使用，只能放在BEGIN END中
		3. 如果WHEN中的值或条件成立，则执行对应的THEN后面的语句，并且结束CASE，如果都不满足，则执行ELSE中的语句或值
		4. ELSE可以省略，如果ELSE省略了，并且所有WHEN条件都不满足，返回NULL
		
*/

-- 案例
-- 创建存储过程，根据传入的成绩，来显示登记，比如传入的成绩：90-100，显示A；80-90，显示B；60-80，显示C；否则，显示D
DELIMITER $
CREATE PROCEDURE test_case(IN score DOUBLE)
BEGIN
	CASE
		WHEN score > 100 OR score < 0 THEN SELECT '成绩输入错误，请输入 0-100 的数字';
		WHEN score <= 100 THEN SELECT 'A';
		WHEN score < 90 THEN SELECT 'B';
		WHEN score < 80 THEN SELECT 'C';
		ELSE SELECT 'D';
	END CASE;
END $;

CALL test_case(90);

-- 3. IF结构
/*
	功能：实现多重分支
	语法：
		IF 条件1 THEN 语句1;
		ELSEIF 条件2 THEN 语句2;
		...
		[ELSE 语句n;]
		END IF;
		
	应用在BEGIN END中
*/

-- 根据传入的成绩，来显示登记，比如传入的成绩：90-100，返回A；80-90，返回B；60-80，返回C；否则，返回D
DELIMITER $
CREATE FUNCTION test_if(score DOUBLE) RETURNS VARCHAR(32)
BEGIN
	IF score > 100 OR score < 0 THEN RETURN '成绩输入错误，请输入 0-100 的数字';
	ELSEIF score <= 100 THEN RETURN 'A';
	ELSEIF score < 90 THEN RETURN 'B';
	ELSEIF score < 80 THEN RETURN 'C';
	ELSE RETURN 'D';
	END IF;
END $;

SELECT test_if(90.5);


-- 二、循环结构
/*
	分类：
		WHILE, LOOP, REPEAT
		
	循环控制：
		ITERATE	类似于 continue
		LEAVE	类似于 break
*/

-- 1. while
/*
	语法：
		[标签: ]WHILE 循环条件 DO
			循环体;
		END WHILE [标签];
*/

-- 2. loop
/*
	语法：
		[标签: ]LOOP
			循环体;
		END LOOP [标签];
*/

-- 3. repeat
/*
	语法：
		[标签：]repeat
			循环体;
		UNTIL 结束循环的条件
		END REPEAT [标签];
*/


-- 没有添加循环控制语句
-- 案例1：批量插入，根据次数插入到admin表中多条记录
DROP PROCEDURE IF EXISTS pro_while1;
DELIMITER $
CREATE PROCEDURE pro_while1(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	WHILE i <= insertCount DO
		INSERT INTO admin(username, `password`) VALUES(CONCAT('Rose', i), CONCAT('000', i));
		SET i = i + 1;
	END WHILE;
END $

CALL pro_while1(100);
SELECT * FROM admin;

-- 2. 添加LEAVE语句
DROP PROCEDURE IF EXISTS pro_while1;
DELIMITER $
CREATE PROCEDURE pro_while1(IN insertCount INT)
BEGIN
	DECLARE i INT DEFAULT 1;
	a: WHILE i <= insertCount DO
		INSERT INTO admin(username, `password`) VALUES(CONCAT('jack', i), CONCAT('000', i));
		IF i >= 20 THEN LEAVE a;
		END IF;
		SET i = i + 1;
	END WHILE a;
END $;
```