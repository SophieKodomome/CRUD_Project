CREATE USER crud WITH PASSWORD 'crud';
ALTER USER crud WITH SUPERUSER;

CREATE DATABASE crud;
\c crud;

CREATE TABLE todolist(
    id Serial PRIMARY KEY,
    task VARCHAR,
    order_task Serial UNIQUE,
    status Boolean,
    remind timestamp,
    due timestamp
);

INSERT INTO todolist(task,status,remind,due) VALUES ('make the app',true,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO todolist(task,status,remind,due) VALUES ('make a todo list',true,'2024-04-16 23:59:59','2024-04-20 23:59:59');
