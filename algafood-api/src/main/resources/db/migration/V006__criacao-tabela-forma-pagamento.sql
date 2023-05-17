create table restaurante_forma_pagamento (
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	
	primary key (restaurante_id, forma_pagamento_id)
) engine=InnoDB default charset=utf8;


create table forma_pagamento (
	id bigint not null auto_increment,
	descricao varchar(60) not null,
	primary key (id)
) engine=InnoDB default charset=utf8;


alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_forma_pagto
foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

