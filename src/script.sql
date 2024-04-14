CREATE USER crud WITH PASSWORD 'crud';
ALTER USER crud WITH SUPERUSER;

CREATE DATABASE crud;
\c crud;

CREATE TABLE todolist(
    id Serial PRIMARY KEY,
    task VARCHAR,
    order Serial UNIQUE,
    status Boolean,
    remind timestamp,
    due timestamp,
);