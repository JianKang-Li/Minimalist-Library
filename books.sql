CREATE DATABASE books

USE  books
CREATE TABLE books(
bname VARCHAR(20) UNIQUE PRIMARY KEY,
bauthor VARCHAR(10),
bnumber INT,
babout TEXT,
bclass VARCHAR(20),
bpostion VARCHAR(20),
bprice NUMERIC(6,2),
bpress VARCHAR(50),
bpresstime DATE
);

CREATE TABLE users(
uname VARCHAR(20) UNIQUE PRIMARY KEY,
usex CHAR(4),
upswd VARCHAR(20),
utel VARCHAR(20),
uemail VARCHAR(20),
uah TEXT,
about TEXT,
signature TEXT,
school TEXT
);

CREATE TABLE admins(
ano INT PRIMARY KEY AUTO_INCREMENT,
aname VARCHAR(20),
apswd VARCHAR(20)
);

CREATE TABLE borrow(
uname VARCHAR(10) NOT NULL,
bname VARCHAR(10) NOT NULL,
happentime DATETIME NOT NULL,
backtime DATETIME NOT NULL,
PRIMARY KEY(uname,bname,happentime),
CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (uname) REFERENCES  users(uname),
CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (bname) REFERENCES books(bname)
)


INSERT INTO users
VALUES ('ljk','男','123456','11903991008','11903991008@cqut.com','休闲、名著','一个大学生','做自己就好','cqut'),
('cqut','男','123456','1190307','1190307@cqut.com','名著','一个大学','教书育人','cqut')


INSERT INTO admins
VALUES (NULL,'admin','123456')



INSERT INTO books
VALUES ('白夜行','东野圭吾','2','故事围绕着一对有着不同寻常情愫的小学生展开。1973年，大阪的一栋废弃建筑内发现了一具男尸，此后19年，嫌疑人之女雪穗与被害者之子桐原亮司走上截然不同的人生道路，一个跻身上流社会，一个却在
底层游走，而他们身边的人，却接二连三地离奇死去，警察经过19年的艰苦追踪，终于使真相大白','推理悬疑,小说','3-11-10','24','南海出版公司','2013-01-01'),

INSERT INTO books
VALUES ('西游记','吴承恩','3','主要描写了孙悟空出世及大闹天宫后，遇见了唐僧、猪八戒、沙僧和白龙马，西行取经，一
路上历经艰险、降妖伏魔，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事','名著','4-11-10','33','人民文学出版社','2001-10-11'),


INSERT INTO users
VALUES ('cqu',NULL,'124567','12367890','1236@cqut.com',NULL,NULL,NULL,NULL),
('cq',NULL,'12567','12367890','1234@cqut.com',NULL,NULL,NULL,NULL),
('ly',NULL,'12346','1234567','12348@cqut.com',NULL,NULL,NULL,NULL),
('c',NULL,'1267','7890','1236@cqut.com',NULL,NULL,NULL,NULL),
('lk',NULL,'1256','1237890','12343@cqut.com',NULL,NULL,NULL,NULL),
('cqut1',NULL,'1567','1234567890','1236@cqut.com',NULL,NULL,NULL,NULL),
('ycl',NULL,'1256','1267890','12340@cqut.com',NULL,NULL,NULL,NULL),
('xsx',NULL,'1267','127890','1230@cqut.com',NULL,NULL,NULL,NULL),
('hjf',NULL,'12346','123890','12341@cqut.com',NULL,NULL,NULL,NULL),
('ct',NULL,'123567','123890','12346@cqut.com',NULL,NULL,NULL,NULL),
('zyx',NULL,'126','123456','12348@cqut.com',NULL,NULL,NULL,NULL),
('cqt',NULL,'1267','127890','1233@cqut.com',NULL,NULL,NULL,NULL),
('jk',NULL,'1256','123890','12346@cqut.com',NULL,NULL,NULL,NULL),
('cut',NULL,'1267','12390','1232@cqut.com',NULL,NULL,NULL,NULL),
('zok',NULL,'126','1234590','123409@cqut.com',NULL,NULL,NULL,NULL),
('cts',NULL,'12567','1234590','12300@cqut.com',NULL,NULL,NULL,NULL),
('loy',NULL,'12356','12345','123411@cqut.com',NULL,NULL,NULL,NULL)

INSERT INTO borrow VALUES("lj","白夜行","2001-10-13",'2021-12-20')
INSERT INTO borrow VALUES("ljk","西游记","2021-9-1",'2021-10-1')

SELECT * FROM borrow;
SELECT * FROM books;
SELECT * FROM users;
SELECT * FROM admins;

DROP TABLE borrow;
DROP TABLE users;
DROP TABLE books;
DROP TABLE admins;

SELECT * FROM books LIMIT 10,8
SELECT * FROM users WHERE uname LIKE '%c%' OR usex LIKE '%c%' OR uemail LIKE '%c%'
