-- Innitial script to create table and initial data
DROP TABLE cities;
CREATE TABLE cities (
	id NUMBER GENERATED BY DEFAULT AS IDENTITY,
	city VARCHAR(128) NOT NULL, 
	state VARCHAR(128) NOT NULL
);


-- Users table to manage access to API
DROP TABLE users;
CREATE TABLE users (
	id NUMBER GENERATED BY DEFAULT AS IDENTITY,
	fullname VARCHAR(128) NOT NULL,
	email varchar(256) NOT NULL,
	username varchar(128) NOT NULL,
	passwd varchar(512) NOT NULL,
	is_active NUMBER(1) DEFAULT 0 NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
