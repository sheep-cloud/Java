# 1. 获得指定范围内的随机数
DROP FUNCTION IF EXISTS random_int;
DELIMITER $
CREATE FUNCTION random_int(start_num INT, end_num INT) RETURNS INT
BEGIN
/*
	获得指定范围内的随机数
	
	@params start_num 最小数（包含）
	@params end_num 最大数（包含）
	@return 随机数
*/
	DECLARE i INT DEFAULT 0;
	# 若要在i ≤ R ≤ j 这个范围得到一个随机整数R ，需要用到表达式 FLOOR(i + RAND() * (j – i + 1))。
	SET i = FLOOR(start_num + RAND() * (end_num - start_num + 1));
	RETURN i;
END $;

SELECT random_int(1, 52);

# 2. 获得一个随机的字符串（a-z，A-Z）
DROP FUNCTION IF EXISTS random_string;
DELIMITER $
CREATE FUNCTION random_string(len INT) RETURNS VARCHAR(256)
BEGIN
/*
	获得一个随机的字符串（a-z，A-Z）
	
	@params len 字符串的长度
	@return 随机字符串
*/
	DECLARE base_char VARCHAR(32) DEFAULT 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	DECLARE return_str VARCHAR(256) DEFAULT '';
	DECLARE base_length INT DEFAULT LENGTH(base_char);
	DECLARE i INT DEFAULT 0;
	WHILE i < len DO
		SET return_str = CONCAT(return_str, SUBSTR(base_char, random_int(1, base_length), 1));
		SET i = i + 1;
	END WHILE;
	RETURN return_str;
END $;

SELECT random_string(10);

# 3. 获取一个随机日期
DROP FUNCTION IF EXISTS random_dateTime;
DELIMITER $
CREATE FUNCTION random_dateTime(start_date VARCHAR(32), end_date VARCHAR(32)) RETURNS DATETIME
BEGIN
/*
	获取随机日期，范围 start_date ~ end_date
*/
	DECLARE return_dateTime DATETIME DEFAULT NULL;
	SET return_dateTime = FROM_UNIXTIME(random_int(UNIX_TIMESTAMP(start_date), UNIX_TIMESTAMP(end_date)));
	RETURN return_dateTime;
END $;

SELECT random_date('1963-01-01', '2000-01-01');