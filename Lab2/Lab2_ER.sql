create database TCU_HieuNguyen;

create table student (sid INT(10), ssn INT(9), StuName VARCHAR(20), CurAddress VARCHAR(20), LocalTel INT(9), PermAddress VARCHAR(20), PermTel INT(9), Sex CHAR, Bdate DATE, GPA DOUBLE(3,1), PRIMARY KEY (sid));

create table department (DId INT(10), DeptName VARCHAR(20), DeptAddress VARCHAR(20), Tel INT(9), PRIMARY KEY (Did));

create table DegreeProgram (PId INT(10), ProgName VARCHAR(20), ProgType VARCHAR(20), UnivReq VARCHAR(20), CollReq VARCHAR(20), DeptReq VARCHAR(20), PRIMARY KEY (Pid)); 

create table CourseDescription (CNo INT(10), Title VARCHAR(20), Credits INT(1), Description VARCHAR(100), PRIMARY KEY(CNo));

create table CourseOffering (SeqId INT(10), Semester VARCHAR(10), Year INT(4), PRIMARY KEY (SeqId));

create table TextBook (ISBN INT(10), TextName VARCHAR(20), Publisher VARCHAR(20), PRIMARY KEY (ISBN));

create table Faculty (FacSSNo INT(10), FacName VARCHAR(20), OfficeAddress VARCHAR(20), Tel INT(9), PRIMARY KEY (FacSSNo));  

show tables;

