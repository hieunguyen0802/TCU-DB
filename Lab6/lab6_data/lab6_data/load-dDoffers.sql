LOAD DATA LOCAL INFILE "D:\\lab6_data\\ddoffers.dat"
INTO TABLE DDoffers
FIELDS ENCLOSED BY '"' TERMINATED BY ','
LINES TERMINATED BY '\r\n';
