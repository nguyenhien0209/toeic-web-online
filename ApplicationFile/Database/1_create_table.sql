use toeiconline;

create table user(
	userid bigint not null primary key auto_increment,
  name varchar(255) null,
  password varchar(255) null,
  fullname varchar(300) not null,
  createddate timestamp null

);

create table role(
	roleId bigint not null primary key auto_increment,
  name varchar(100) null
);