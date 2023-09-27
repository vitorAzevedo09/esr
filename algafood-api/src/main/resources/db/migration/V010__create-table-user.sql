create table users (
	id bigint not null auto_increment,
	name varchar(80) not null,
	email varchar(255) not null,
	password varchar(255) not null,
	register_at datetime not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;

create table user_group (
	user_id bigint not null,
	group_id bigint not null,
	
	primary key (user_id, group_id)
) engine=InnoDB default charset=utf8;


alter table user_group add constraint fk_user_group_group
foreign key (group_id) references groups (id);

alter table user_group add constraint fk_user_group_user
foreign key (user_id) references users (id);
