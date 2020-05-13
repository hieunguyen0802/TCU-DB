CREATE DATABASE IF NOT EXISTS UNIVERSITY_HieuNguyen;
USE UNIVERSITY_HieuNguyen;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS faculty;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS courseoffering;
DROP TABLE IF EXISTS coursedescription;
DROP TABLE IF EXISTS dept_offers_coursedesc;
DROP TABLE IF EXISTS textbook;
SET FOREIGN_KEY_CHECKS=1;  
create table faculty(
	facssno varchar(9) primary key,
    facname varchar(30)
);
 
 insert into faculty values ('000000000', 'John Doe');
 insert into faculty values ('111111111', 'Mark Smith');
 select * from faculty;
 
 create table department (
 did integer(7) primary key,
 deptname varchar(30),
 chair varchar(9) unique,
 FOREIGN KEY (chair) REFERENCES faculty(facssno));
 
 insert into department values (1, 'CS', '000000000');
 select * from department;
 
/*
insert into department values  (2, 'MS', '000000000');
*/

create table coursedescription (
cno varchar (10) primary key,
title varchar(50),
credits integer (1),
description varchar(200));

create table courseoffering (
seqid integer (5) primary key,
semester varchar (6),
year integer(4),
descriptionno varchar(10),
instructor varchar(9),
foreign key (descriptionno) references coursedescription(cno),
FOREIGN KEY (instructor) REFERENCES faculty(facssno));

create table dept_offers_coursedesc(
did integer (7),
cno varchar(10),
primary key (did,cno),
foreign key (did) references department(did),
foreign key (cno) references coursedescription(cno));

create table textbook ( 
isbn varchar (10) primary key,
textname varchar (30),
publisher varchar (50));

create table has (
seqid integer (5),
bookno varchar(10),
primary key (seqid, bookno),
foreign key (seqid) references courseoffering(seqid),
foreign key (bookno) references textbook(isbn));