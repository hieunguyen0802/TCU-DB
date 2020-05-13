/*1*/ 

Select title, year, published_by, first_name, last_name
From Book join (Edits join Editor on eSSN=SSN) on ISBN = bISBN;

/*2*/ 
Select title
From Book join Write on ISBN = bISBN join Author on aSSN=SSN 
Where last_name not in (‘Smith’,’Doe’);

/*3*/
Select first_name, last_name 
From Editor join Edits e join Writes w on e.eSSN = w.aSSN and e.bISBN = w.bISBN where SSN = e.eSSN;

/*4*/
Select title 
from Book 
where published_by =”ABC” ORDER BY price DESC limit 1;

/*5*/
Select distinct e.first_name, e.last_name 
From Editor e join Edits ed join Author a join Writes w on a.SSN = w.aSSN and ed.ISBN = w.bISBN and e.SSN = ed.eSSN 
where (a.first_name = “John and a.last_name = “Smith”);

/*6*/ 
Select title
From Writes w join Book b on w.bISBN = b.ISBN group by aSSN having count(*) > 3

/*7*/ 
Select b.published_by, B2008.book_count_08, B2009.book_count_09, (B2009.book_count_09 – B2008.book_count_08)*100 / B2008.book_count_08
From Book b, (select count(*) AS book_count_08 FROM Book where year = 2008 and published_by b.published_by) AS B2008, (select count(*) AS book_count_09 FROM Book where year = 2009 and published_by b.published_by) AS B2009 group by b.published_by;

/*8*/ 
Update Editor e set e.book_count = (Select count(*) 
From Edits ed where e.SSN = ed.SSN group by ed.eSSN);

/*9*/
Select p.name, b.title 
From Book b, Publisher b, Editor e, Edit ed 
Where b.published_by = p.name and p.name = e.works_for and e.SSN = ed.eSSN and ed.bISBN = b.ISBN group by p.name, b.title having count (*) = (select max(temp) from (select p.name, b.title, count(*) as temp from Book b, Publisher p, Editor e, Edit ed where b.published_by = p.name and p.name = e.works_for and e.SSN = ed.eSSN and ed.bISBN = b.ISBN group by p.name, b.title));

/*10*/
Select p.name, SUM(Salary) as total_salary 
From Publisher p join Editor e on p.name=e.works_for 
Where p.city =’OKC’ group by p.name order by total_salary DESC limit 3;
