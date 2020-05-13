/*
Write your answers after each question.
Make sure they are executable.
Due Dec 11
Hieu Nguyen
*/


/*
1.Write a query in SQL to display those employees who contain a letter z to their first name 
and also display their last name, department, city, and state province.
*/
Select e.first_name,e.last_name,
   d.department_name, l.city, l.state_province
     from employees e 
      join departments d  
       on e.department_id = d.department_id 
        join locations l 
         on d.location_id = l.location_id 
           where e.first_name like'%z%';
/*
2.Write a query in SQL to display all departments that don’t have any employees.
*/
select distinct d1.department_name 
	from departments d1 
		where not exists (select distinct e1.department_id 
			from emp_details_view e1 
				where d1.department_id = e1.department_id);

/*
3.Write a query in SQL to display the first and last name and salary for those employees 
who earn less than the employee earn whose number is 182. You are required to use Theta join for this question.
*/
select e1.first_name, e1.last_name, e1.salary 
	from emp_details_view e1 join emp_details_view e2 on e1.salary < e2.salary 
		where e2.employee_id = 182;

/*
4.Write a query in SQL to display the first name and last name of every employee and 
the first name and last name of his/her manager’s manager.
*/
select e1.first_name, e1.last_name, e2.first_name, e2.last_name 
	from employees e1 join employees e2 
		where e1.manager_id = e2.employee_id;

/*
5.Write a query in SQL to display the first name, last name, and department number for those 
employees who works in the same department as the employee who holds the last name as Taylor.
*/
select e1.first_name, e1.last_name, e1.department_id 
	from emp_details_view e1 
		where exists (select * from emp_details_view e2 
			where e2.last_name = 'Taylor' and e1.department_id = e2.department_id);

/*
6.Write a query in SQL to display the job title, department name, first and last name of employee, 
and starting date for all the jobs which started on or after 1st January, 1993 and ending with on or before 31st August, 1997.
*/
select e1.job_title, e1.department_name, e1.first_name, e1.last_name 
	from emp_details_view e1 join job_history j1 on e1.employee_id = j1.employee_id 
		where j1.start_date >= '19930101' and j1.end_date <= '19970831';

/*
7.Write a query in SQL to display every manager’s id, first and last name, and the 
lowest salary of his/her direct subordinates.
*/
create view manager
as
select e2.employee_id, e2.first_name, e2.last_name, e1.salary from employees e1 join employees e2 where e1.manager_id = e2.employee_id;

select * from manager m1 where not exists ( select * from manager m2 where m2.salary < m1.salary and m1.employee_id = m2.employee_id);

/*
8.Write a query in SQL to display the country name, city, and number of those 
departments where at least 2 employees are working.
*/
select country_name,city, count (department_id)
	from countries 
		join locations using (country_id) 
		join departments using (location_id) 
where department_id IN 
    (select department_id 
		from employees 
	 group by department_id 
	 having count (department_id)>=2)
group by country_name,city;

/*
9.Write a query to display the employee number, first name and last name and 
job title for all employees whose salary is more than ANY average salary of any department.
*/
select e1.employee_id, e1.first_name, e1.last_name, e1.job_title 
	from emp_details_view e1 
		where e1.salary > (select avg(e2.salary) from emp_details_view e2);
/*

