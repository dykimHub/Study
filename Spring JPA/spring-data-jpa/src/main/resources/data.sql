insert into course(id, name, created_date, last_updated_date, is_deleted)
values(10001, 'JPA in 50 Steps', CURRENT_DATE(), CURRENT_DATE(), false),
(10002, 'Spring in 50 Steps', CURRENT_DATE(), CURRENT_DATE(), false),
(10003, 'Spring Boot in 100 Steps', CURRENT_DATE(), CURRENT_DATE(), false),
(10004, 'Dummy1', CURRENT_DATE(), CURRENT_DATE(), false),
(10005, 'Dummy2', CURRENT_DATE(), CURRENT_DATE(), false),
(10006, 'Dummy3', CURRENT_DATE(), CURRENT_DATE(), false),
(10007, 'Dummy4', CURRENT_DATE(), CURRENT_DATE(), false),
(10008, 'Dummy5', CURRENT_DATE(), CURRENT_DATE(), false);

insert into passport(id, number)
values(40001, 'E123456'),
(40002, 'N12345'),
(40003, 'L123890');

insert into student(id, name, passport_id)
values(20001,'Ranga', 40001),
(20002, 'Adam', 40002),
(20003, 'Jane', 40003);

insert into review(id, rating, description, course_id)
values(50001, '5', 'Great Course', 10001),
(50002, '4', 'Wonderful Course', 10001),
(50003, '5', 'Awesome Course', 10003);

insert into student_course(student_id, course_id)
values(20001,10001),
(20002,10001),
(20003,10001),
(20001,10003);
