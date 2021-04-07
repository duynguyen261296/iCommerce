-- create table users
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    user_id  int identity,
    username varchar(45) not null,
    password varchar(60) not null,
    email    varchar(60) not null,
    enabled  bit         not null,

    constraint PK_USERS primary key (user_id),
    constraint UNIQUE_USERS unique (username)
);

-- create table roles
DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    role_id int identity,
    role    varchar(10) not null
        constraint CKC_ROLE check (role in ('ROLE_USER', 'ROLE_ADMIN')),

    constraint PK_ROLES primary key (role_id)
);

-- create table user_roles
DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles
(
    user_role_id int identity,
    username     varchar(45) not null,
    role         varchar(10)  not null
        constraint CKC_ROLE check (role in ('ROLE_USER', 'ROLE_ADMIN')),

    constraint PK_USER_ROLES primary key (user_role_id),
    constraint UNIQUE_USERS_ROLES unique (username, role),
    constraint FK_USER_ROLES_USER foreign key (username) references users(username),
    constraint FK_USER_ROLES_ROLES foreign key (role) references roles(role)
);

-- create table products
DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    product_id int identity,
    name       varchar(45) not null,
    price      double      not null,
    brand      varchar(45) not null,
    quantity   int         not null,

    constraint PK_PRODUCTS primary key (product_id)
);

-- create table orders
DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    order_id     int identity,
    full_name    varchar(45) not null,
    phone        varchar(12) not null,
    address      varchar(60) not null,
    email        varchar(45) not null,
    product_name varchar(45) not null,
    quantity     int         not null,
    sum          double      not null,

    constraint PK_ORDERS primary key (order_id)
);

-- insert data for table users
INSERT INTO users (username, password, email, enabled)
VALUES ('user_1', 'password1', 'user_1@gmail.com', 1),
       ('user_2', 'password2', 'user_2@gmail.com', 1),
       ('admin_1', 'password3', 'admin_1@gmail.com', 1);

-- insert data for table roles
INSERT INTO roles (role)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

-- insert data for table user_roles
INSERT INTO user_roles (username, role)
VALUES ('user_1', 'ROLE_USER'),
       ('user_2', 'ROLE_USER'),
       ('admin_1', 'ROLE_ADMIN');

-- insert data for table products
INSERT INTO products (name, price, brand, quantity)
VALUES ('IphoneX', '1000', 'Apple', '10'),
       ('Iphone11', '1200', 'Apple', '15'),
       ('Iphone12', '1500', 'Apple', '20'),
       ('Samsung Galaxy Note 10', '1000', 'Samsung', '10'),
       ('Samsung Galaxy Note 20', '1200', 'Samsung', '15'),
       ('Samsung Galaxy Note 21', '1500', 'Samsung', '18'),
       ('Xiaomi Redmi Note 10', '200', 'Xiaomi', '30'),
       ('Xiaomi Mi 11', '800', 'Xiaomi', '15'),
       ('Poco F3', '500', 'Xiaomi', '20'),
       ('VSmart Star 4', '300', 'VSmart', '20');