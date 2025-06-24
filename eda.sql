DROP DATABASE IF EXISTS eda;
CREATE DATABASE IF NOT EXISTS eda;
use eda;

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,
    point      INT,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE products
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    price      INT          NOT NULL,
    created_at DATETIME,
    updated_at DATETIME
);

CREATE TABLE point_history
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT,
    change_amount INT NOT NULL,
    reason        VARCHAR(200),
    created_at    DATETIME,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT NOT NULL,
    total_price INT    NOT NULL,
    used_point  INT,
    created_at  DATETIME,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_detail
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id   BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity   INT    NOT NULL,
    price      INT    NOT NULL COMMENT '주문시점의 가격',

    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
)
