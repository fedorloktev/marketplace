-- Создание таблицы пользователей
create table client (
	id serial primary key,
	name varchar(60) not null,
	address varchar(100),
	login varchar(30) not null,
	password varchar(30) not null
)

-- Создание таблицы продуктов
create table product (
	id serial primary key,
	user_id INT not null,
	name varchar(60) not null,
	description varchar(200) not null,
	foreign key (user_id) references client (id)
)

-- Создание таблицы лотов
create table lot (
	id serial primary key,
	product_id INT not null,
	bidder_id INT,
	best_price DECIMAL(9,2),
	start_price DECIMAL(9,2) not null,
	stop_date TIMESTAMP not null,
	foreign key (product_id) references product (id),
	foreign key (bidder_id) references client (id)
)
