CREATE TABLE IF NOT EXISTS worker (
id IDENTITY PRIMARY KEY,
name_worker VARCHAR(1000) NOT NULL CHECK (LENGTH(name_worker) >= 2 AND LENGTH(name_worker) <= 1000),
birthday DATE NOT NULL CHECK  (birthday>'1900-12-31'),
level_skill  VARCHAR(10) NOT NULL CHECK  (level_skill in('Trainee','Junior','Middle','Senior') ),
salary NUMERIC (6,0) NOT NULL
CONSTRAINT chk_salary CHECK (salary>=100 AND salary<=100000)
);


CREATE TABLE IF NOT EXISTS client (
id IDENTITY PRIMARY KEY,
name VARCHAR(1000) NOT NULL CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 1000)
);


CREATE TABLE IF NOT EXISTS project (
id IDENTITY PRIMARY KEY,
start_date DATE NOT NULL,
finish_date DATE NOT NULL,
client_id BIGINT,
foreign key (client_id) references client (id)
on update cascade
on delete cascade
);

CREATE TABLE IF NOT EXISTS project_worker(
project_id BIGINT NOT NULL,
worker_id BIGINT NOT NULL,
primary key(project_Id,worker_Id),
foreign key (project_Id) references project (id),
foreign key (worker_id) references worker (id)
);