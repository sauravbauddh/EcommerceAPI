drop database ecommercedb;
drop user ecommerce;
create user ecommerce with password 'Kabcd02.';
create database ecommercedb with template=template0 owner=ecommerce;

\connect ecommercedb;
alter default privileges grant all on tables to ecommerce;
alter default privileges grant all on sequences to ecommerce;

create table users(
    user_id int primary key not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(30) not null,
    password text not null
);

create table products(
    product_id int primary key not null,
    name varchar(30) not null,
    description varchar(50) not null,
    price NUMERIC(10,2) not null
);

CREATE TABLE cart (id INT PRIMARY KEY NOT NULL, user_id INT NOT NULL,FOREIGN KEY (user_id) REFERENCES users(user_id));

CREATE TABLE cart_item (
                           id INT PRIMARY KEY NOT NULL,
                           cart_id INT NOT NULL,
                           product_id INT NOT NULL,
                           FOREIGN KEY (cart_id) REFERENCES cart(id),
                           FOREIGN KEY (product_id) REFERENCES products(product_id)
);


create table orders(
    order_id int primary key not null,
    user_id int not null,
    product_id int not null
);
alter table orders add constraint order_user_fk foreign key (user_id) references users(user_id);
alter table orders add constraint order_product_fk foreign key (product_id) references products(product_id);

create sequence users_seq increment 1 start 1;
create sequence product_seq increment 1 start 1;
create sequence cart_seq increment 1 start 1;
create sequence cart_item_seq increment 1 start 1;


