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