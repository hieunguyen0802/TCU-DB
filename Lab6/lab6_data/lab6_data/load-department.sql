LOAD DATA LOCAL INFILE "D:\\lab6_data\\department.dat"
INTO TABLE Department
FIELDS ENCLOSED BY '"' TERMINATED BY ','
LINES TERMINATED BY '\r\n';