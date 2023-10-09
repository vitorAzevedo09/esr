create table restaurant_user (
	user_id bigint not null,
	restaurant_id bigint not null,
	
	primary key (restaurant_id, user_id)
) engine=InnoDB default charset=utf8;


alter table restaurant_user add constraint fk_restaurant_user_restaurant
foreign key (restaurant_id) references restaurants (id);

alter table restaurant_user add constraint fk_restaurant_user_user
foreign key (user_id) references users (id);
