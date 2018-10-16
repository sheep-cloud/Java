-- 添加表、字段注释
SELECT * FROM locations;
SHOW FULL COLUMNS FROM locations;
ALTER TABLE locations COMMENT '地址表';
ALTER TABLE locations MODIFY COLUMN location_id INT(11) COMMENT '地址ID';
ALTER TABLE locations MODIFY COLUMN street_address VARCHAR(40) COMMENT '街道地址';
ALTER TABLE locations MODIFY COLUMN postal_code VARCHAR(12) COMMENT '邮政编码';
ALTER TABLE locations MODIFY COLUMN city VARCHAR(30) COMMENT '市';
ALTER TABLE locations MODIFY COLUMN state_province VARCHAR(25) COMMENT '州（省）';
ALTER TABLE locations MODIFY COLUMN country_id VARCHAR(2) COMMENT '国家ID';

SELECT * FROM departments;
SHOW FULL COLUMNS FROM departments;
ALTER TABLE departments COMMENT '部门表';
ALTER TABLE departments MODIFY COLUMN department_id INT(4) COMMENT '部门ID';
ALTER TABLE departments MODIFY COLUMN department_name VARCHAR(3) COMMENT '部门名称';
ALTER TABLE departments MODIFY COLUMN manager_id INT(6) COMMENT '部门经理ID';
ALTER TABLE departments MODIFY COLUMN location_id INT(11) COMMENT '地址ID';

SELECT * FROM employees;
SHOW FULL COLUMNS FROM employees;
ALTER TABLE employees COMMENT '员工表';
ALTER TABLE employees MODIFY COLUMN employee_id INT(6) COMMENT '员工ID';
ALTER TABLE employees MODIFY COLUMN first_name VARCHAR(25) COMMENT '名';
ALTER TABLE employees MODIFY COLUMN last_name VARCHAR(25) COMMENT '姓';
ALTER TABLE employees MODIFY COLUMN email VARCHAR(25) COMMENT '邮箱';
ALTER TABLE employees MODIFY COLUMN phone_number VARCHAR(20) COMMENT '电话号码';
ALTER TABLE employees MODIFY COLUMN job_id VARCHAR(10) COMMENT '工作ID';
ALTER TABLE employees MODIFY COLUMN salary DOUBLE(10,2) COMMENT '工资';
ALTER TABLE employees MODIFY COLUMN commission_pct DOUBLE(4,2) COMMENT '佣金pct';
ALTER TABLE employees MODIFY COLUMN manager_id INT(6) COMMENT '部门经理ID';
ALTER TABLE employees MODIFY COLUMN department_id INT(4) COMMENT '部门ID';
ALTER TABLE employees MODIFY COLUMN hiredate DATETIME COMMENT '入职日期';

SELECT * FROM jobs;
SHOW FULL COLUMNS FROM jobs;
ALTER TABLE jobs COMMENT '工作表';
ALTER TABLE jobs MODIFY COLUMN job_id VARCHAR(10) COMMENT '工作ID';
ALTER TABLE jobs MODIFY COLUMN job_title VARCHAR(35) COMMENT '职称';
ALTER TABLE jobs MODIFY COLUMN min_salary INT(6) COMMENT '最低工资';
ALTER TABLE jobs MODIFY COLUMN max_salary INT(6) COMMENT '最高工资';