-- CREATE TABLE `users` (
--     `id` INT AUTO_INCREMENT PRIMARY KEY,
--     `password` VARCHAR(255),
--     `name` VARCHAR(255),
--     `birth_date` DATE
-- );

-- script는 작은 따옴표 써야함
insert into `user` (password, name, birth_date) 
values('cho1234', 'cho', '1998-01-25'),
('son1234', 'son', '1992-07-08');

insert into `product` (name, price) 
values('ball','50000'),
('boots','100000'),
('uniform(HomeKit)','150000');
