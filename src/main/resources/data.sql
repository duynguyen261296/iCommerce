-- create table users
DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id     int             identity,
    username    varchar(45)     not null,
    password    varchar(60)     not null,
    email       varchar(60)     not null,
    enabled     bit             not null,

    constraint PK_USERS primary key (user_id)
);

-- create table user_roles
DROP TABLE IF EXISTS user_roles;
CREATE TABLE user_roles (
    user_role_id    int             identity,
    user_id         int             not null,
    role            varchar(4)      not null
    constraint CKC_ROLE check (role in ('USER', 'ADMIN')),

    constraint PK_USER_ROLES primary key (user_role_id),
    constraint FK_USER_ROLES_USERS foreign key (user_id) references users (user_id)
);

-- create table products
DROP TABLE IF EXISTS products;
CREATE TABLE products (
    product_id      int             identity,
    name            varchar(45)     not null,
    price           double          not null,
    brand           varchar(45)     not null,
    quantity        int             not null,

    constraint PK_PRODUCTS primary key (product_id)
);

-- create table orders
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    order_id        int             identity,
    product_id      int             not null,
    full_name       varchar(45)     not null,
    phone           varchar(12)     not null,
    address         varchar(60)     not null,
    email           varchar(45)     not null,

    constraint PK_ORDERS primary key (order_id),
    constraint FK_ORDERS_PRODUCTS foreign key (product_id) references products (product_id),
    constraint FK_ORDERS_USERS foreign key (email) references users (email)
);