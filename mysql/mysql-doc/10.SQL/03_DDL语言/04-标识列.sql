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