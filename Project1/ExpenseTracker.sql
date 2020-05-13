CREATE DATABASE expensetracker;
USE expensetracker;
CREATE TABLE activity (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200),
  money DOUBLE,
  account VARCHAR(100),/*where does the money come from or go to*/
  createtime DATE,
  description VARCHAR(1000)
);

INSERT  INTO activity(name,money,account,createtime,description) VALUES 
('Dining Spending',247,'ChaseBank Credit Card','2016-03-02','Dutch Burger'),
('Salary Income',3000,'Cash','2016-03-15','September Salary'),
('Books Spending',200,'Cash','2016-04-02','Calculus Book'),
('Dining Spending',325,'Cash','2016-06-18','Eat at TCU Cafe'),
('Stock Income',8000,'WellsFargo Saving Account','2016-10-28','Apple Stock is great!'),
('Stock Income',5000,'WellsFargo Saving Account','2016-10-28','Oracle Stock is great!'),
('Salary Income',3000,'ChaseBank Saving Account','2016-10-28','Oct Salary time!'),
('Clothes Spending',5000,'Cash','2016-10-28','JCrew again'),
('Other Spending',20,'Cash','2016-10-29','Lost 20 bucks in library. :('),
('Gas Spending',1200,'ChaseBank Credit Card','2016-10-29','Gas is so expensive'),
('Dining Spending',1000,'ChaseBank Debit Card','2016-10-29','Eat eat eat'),
('Salary Income',3000,'ChaseBank Saving Account','2016-10-30','Nov salary'),
('Flight Ticket Spending',500,'ChaseBank Debit Card','2016-10-30','Fly to LA'),
('Salary Income',3000,'ChaseBank Saving Account','2016-10-30','December Salary');