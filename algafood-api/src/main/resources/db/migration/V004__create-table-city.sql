CREATE TABLE cities (
  id bigint not null auto_increment,
  name varchar(255),
  state_id bigint,
  primary key (id)
) engine=InnoDB;

ALTER TABLE cities 
  ADD CONSTRAINT FKkworrwk40xj58kevvh3evi500 
  FOREIGN KEY (state_id) REFERENCES states (id);
