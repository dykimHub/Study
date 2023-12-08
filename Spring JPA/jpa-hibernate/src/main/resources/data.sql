insert into course(id, name, created_date, last_updated_date)
values(10001, 'JPA in 50 Steps', CURRENT_DATE(), CURRENT_DATE()),
(10002, 'Spring in 50 Steps', CURRENT_DATE(), CURRENT_DATE()),
(10003, 'Spring Boot in 100 Steps', CURRENT_DATE(), CURRENT_DATE());

insert into passport(id, number)
values(40001, 'E123456'),
(40002, 'N12345'),
(40003, 'L123890');

insert into student(id, name, passport_id)
values(20001,'Ranga', 40001),
(20002, 'Adam', 40002),
(20003, 'Jane', 40003);

insert into review(id, rating, description)
values(50001, '5', 'Great Course'),
(50002, '4', 'Wonderful Course'),
(50003, '5', 'Awesome Course');