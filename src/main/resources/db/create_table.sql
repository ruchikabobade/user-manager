create database `topcoder-challenge`;

use `topcoder-challenge`;

create table user (
  id bigint not null auto_increment,
  username varchar(255),
  full_name varchar(255),
  role varchar(255),
  email_address varchar(255),
  status varchar(255)
  primary key (id)
) engine=InnoDB;