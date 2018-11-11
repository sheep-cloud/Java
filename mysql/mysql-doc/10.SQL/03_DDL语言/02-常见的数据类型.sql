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