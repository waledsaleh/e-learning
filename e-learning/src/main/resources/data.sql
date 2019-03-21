insert into student(id,name,email,username,password,date_of_birth,gender)
            values(1,'Ahmed','ahmed@email.com','ahmed_test','ahmed-test','1994-03-05','male');

insert into course(id,name,description,publish_date,last_updated,total_hours,instructor)
            values(1,'Microservices','description','2019-03-05','2019-03-06',5,'Ahmed');

insert into course_registration(id,student_id,course_id,is_register)values(1,1,1,false);