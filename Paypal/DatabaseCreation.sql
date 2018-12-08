CREATE DATABASE paypal;
USE paypal;

CREATE TABLE users (
	id int auto_increment primary key,
	first_name varchar(25) not null,
	last_name varchar(35) not null,
	balance real not null default 0
);

CREATE TABLE transactions (
	id int auto_increment primary key,
	user_from int,
	user_to int,
	transaction_amount real not null,
	transaction_date timestamp not null default now()
);