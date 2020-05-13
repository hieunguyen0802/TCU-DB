LOAD DATA LOCAL INFILE "D:\\lab6_data\\faculty.dat"
INTO TABLE faculty
FIELDS ENCLOSED BY '"' TERMINATED BY ','
LINES TERMINATED BY '\r\n'
(FacName,FacSSNo,OfficeAddress);
