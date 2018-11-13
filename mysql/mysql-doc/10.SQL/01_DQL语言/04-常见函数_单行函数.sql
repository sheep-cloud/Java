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

-- 2.3. FLOOR 向下取整，返回<=该参数的最小整数
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