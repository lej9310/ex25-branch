SHOW DATABASES;
CREATE DATABASE testdb;
USE testdb;

SHOW TABLES;

CREATE TABLE student(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	NAME VARCHAR(100) NOT NULL,
	email VARCHAR(100),
	age INT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
SELECT * FROM student;
SELECT * FROM student ORDER BY id DESC;
SELECT * FROM student WHERE id = 1;

-- 샘플데이터
INSERT INTO student(NAME, email, age) VALUES 
('김철수', 'kim@example.com', 20),
('이영희', 'kim@example.com', 22),
('박민수', 'kim@example.com', 21);
INSERT INTO student(NAME, email, age) VALUES 
('김철수', '', 0);
INSERT INTO student(NAME) VALUES 
('김철수');
INSERT INTO student(NAME, email, age) VALUES 
('화이팅', 'kim@example.com', 20);

UPDATE student 
	SET NAME = '김철수_수정', email = 'kim@example.com', age= 20
	WHERE id=1;
	
DELETE FROM student
	WHERE id=8;