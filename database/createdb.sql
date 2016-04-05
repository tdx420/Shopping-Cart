CREATE TABLE `members` (
  `id` int(10) unsigned NOT NULL auto_increment PRIMARY KEY,
  `first_name` varchar(45) NOT NULL   ,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `regdate` datetime NOT NULL
);
create table food
(
	`id` int(10) unsigned NOT NULL auto_increment PRIMARY KEY,
  `name` varchar(45) NOT NULL   ,
   price double
);
create table sidemenu 
(
  `name` varchar(45) NOT NULL   ,
   price double
);


Create table dessert
(
	name varchar(45) NOT NULL,
	price double
);


create table history
(
`id` int(10) unsigned NOT NULL auto_increment PRIMARY KEY,
  `user_name` varchar(45) NOT NULL   ,
  `product_history`  varchar(1545) NOT NULL ,
   total_expense double,
   purchase_date  datetime NOT NULL DEFAULT NOW()
   
);



insert into members value (1, "admin", "admin", "admin@hotmail.com", "tdx", "429",  CURTIME());

insert into food value( 1, "Momo", 12.99);
insert into food value( 2, "Chowmein", 13.99);
insert into food value( 3, "Pangra", 13.99);
insert into food value( 4, "Sukuti", 13.99);
insert into food value( 5, "choyela", 13.99);


insert into sidemenu value( "Coke", 0.99);
insert into sidemenu value( "Pepsi", 0.99);
insert into sidemenu value( "Fanta", 0.50);


insert into dessert value( "Laddu", 0.99);
insert into dessert value( "Barfi", 0.99);
insert into dessert value( "Gulabjammon", 0.50);


