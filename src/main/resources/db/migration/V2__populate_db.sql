insert into worker( name_worker, birthday, level_skill,salary) values('Oliver','1988-04-01','Trainee',450),
('Jack','1989-03-02','Junior',1200),
('Harry','1991-01-03','Middle',2500),
('Jacob ','2000-02-04','Senior',6300),
('Charley','2001-01-05','Junior',1000),
('Thomas','2002-12-06','Middle',2300),
('George','2003-11-07','Senior',6500),
('Oscar','2004-10-08','Junior',1100),
('Emma','2005-09-09','Middle',2400),
('Olivia','2006-01-11','Trainee',450),
('Ava','1988-06-12','Junior',1000),
('Sofia','1988-05-13','Middle',2200),
('Isabel','1988-04-14','Senior',7000);
insert into client (name) values ('James'),
('John'),
('Robert'),
('Michael'),
('William'),
('Mary'),
('Jennifer'),
('Linda'),
('Jessica');insert into project( start_date, finish_date, client_id) values('2022-11-30','2022-12-30',1),
('2023-01-03','2023-03-06',2),
('2023-01-15','2023-06-01',3),
('2015-01-15','2023-01-04',4),
('2022-01-17','2023-02-06',5),
('2020-04-08','2022-12-15',6),
('2019-06-17','2022-05-09',7),
('2019-10-17','2023-01-26',8),
('2016-07-19','2022-09-23',9),
('2016-07-15','2016-10-02',1),
('2017-09-14','2022-11-14',2);
insert into project_worker(project_id,worker_id ) values(1,1),
(1,2),
(1,3),
(2,4),
(2,5),
(2,6),
(2,7),
(3,8),
(4,9),
(4,10),
(4,11),
(4,12),
(5,13),
(6,13),
(6,1),
(7,2),
(8,3),
(9,4),
(10,5),
(10,6),
(11,7),
(11,8);










