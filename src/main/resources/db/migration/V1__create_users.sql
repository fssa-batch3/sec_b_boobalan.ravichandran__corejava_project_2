
CREATE database if not exists boobalan_ravichandran_corejava_project;

use boobalan_ravichandran_corejava_project;
CREATE TABLE if not exists users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    mobile_number LONG NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users (name, email, password, mobile_number) VALUES
('John Doe', 'john@example.com', 'password123', 1234567890),
('Jane Smith', 'jane@example.com', 'test456', 9876543210);

select* from users;


CREATE TABLE if not exists categories_type (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
 is_active BOOLEAN DEFAULT TRUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO categories_type (name) VALUES
('BRANDS'),
('CROP PRODUCTION');
select* from categories_type;

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category_type_id INT,
     is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_type_id) REFERENCES categories_type(id)
);
INSERT INTO categories (name, category_type_id) VALUES
('DHANUKA', 1),
('BIO INSECTICIDES', 2);

select* from categories;

CREATE TABLE IF NOT EXISTS products(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(150) NOT NULL,
product_weight VARCHAR(20) NOT NULL,
description TEXT NOT NULL,
benefits TEXT NOT NULL,
application TEXT NOT NULL,
manufacture VARCHAR(200) NOT NULL,
is_active BOOLEAN DEFAULT TRUE,
category_id INT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (category_id) REFERENCES categories(id)
);
INSERT INTO products (name, product_weight, description, benefits, application,manufacture, category_id) VALUES
('DHANUKA M45 FUNGICIDE', '2kg', 'High-performance fertilizer', 'Fast processing', 'Business and gaming','DHANUKA', 1),
('BIOVITA SEAWEED ORGANIC PLANT GROWTH REGULATOR', '500g', 'Suitable fertilizers for all the crops', 'Use 20 days only per hector', 'ow has for the first time made 100% silicon available','BIOVITA', 2),
('DHANUKA F25', '5kg', 'PLANT GROWTH REGULATOR', 'Fast processing', 'Use 30 days only per hector','DHANUKA', 2);

select* from products;

CREATE TABLE IF NOT EXISTS prices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    price INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    product_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
INSERT INTO prices (price, start_date, product_id)
VALUES (1000, CURRENT_DATE, 1),(1300, CURRENT_DATE, 2),(1500, CURRENT_DATE, 3);

select* from prices;

UPDATE prices
SET end_date = CURRENT_DATE
WHERE product_id = 1
AND end_date IS NULL;

INSERT INTO prices (price, start_date, product_id)
VALUES (1200, CURRENT_DATE, 1);

select* from prices;
drop table prices;
