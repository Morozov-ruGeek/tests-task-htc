drop table if exists users;
create table users
(
    id         identity primary key,
    first_name varchar(128) not null,
    last_name  varchar(128) not null
);
