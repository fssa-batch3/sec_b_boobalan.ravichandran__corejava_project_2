
CREATE database if not exists boobalan_ravichandran_corejava_project;

use boobalan_ravichandran_corejava_project;
CREATE TABLE if not exists users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(240) NOT NULL,
    mobile_number LONG NOT NULL, 
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX mobile_number_unique_index ON users (mobile_number(15));




CREATE TABLE if not exists category_types (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
 is_active BOOLEAN DEFAULT TRUE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    category_type_id INT,
     is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_type_id) REFERENCES category_types(id)
);



CREATE TABLE IF NOT EXISTS products(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(150) NOT NULL,
product_weight VARCHAR(20) NOT NULL,
description TEXT NOT NULL,
benefits TEXT NOT NULL,
application TEXT NOT NULL,
manufacture VARCHAR(200) NOT NULL,
image_url TEXT NOT NULL,
is_active BOOLEAN DEFAULT TRUE,
category_id INT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS prices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    price INT NOT NULL,
    discount INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    product_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);




CREATE TABLE addresses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    address_title VARCHAR(50) NOT NULL,
    street_name VARCHAR(200) NOT NULL,
    location VARCHAR(200) NOT NULL,
    city VARCHAR(200),
    pincode INT NOT NULL,
    person_name VARCHAR(80) NOT NULL,
    state VARCHAR(100) NOT NULL,
    mobile_number LONG NOT NULL,
	user_email VARCHAR(220) NOT NULL ,
    is_active BOOLEAN DEFAULT TRUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES users(email)
);
drop table addresses;
INSERT INTO addresses (address_title, street_name, location, city, pincode, person_name, state, mobile_number, user_email)
VALUES
('Home', '123 Main St', 'Suburbia', 'Cityville', 620015, 'John Doe', 'Tamil Nadu', 6551234567, 'rboomibaln459@gmail.com'),
('Work', '456 Business Blvd', 'Downtown', 'Metropolis', 643261, 'Jane Smith', 'Tamil Nadu', 6859876543, 'rboomibaln459@gmail.com'),
('Others', '789 Beachfront Dr', 'Seaside', 'Resort Town', 628902, 'Vacationer', 'Tamil Nadu', 7551112233, 'rboomibaln459@gmail.com'),
('Office', '101 Corporate Ave', 'Business District', 'Commerce City', 620017, 'Businessperson', 'Tamil Nadu', 8554445555, 'rboomibaln459@gmail.com'),
('Home', '321 Family Lane', 'Suburbville', 'Family Town', 631456, 'Family Member', 'Tamil Nadu', 9557778888, 'rboomibaln459@gmail.com');
select* from addresses;
DELETE FROM addresses;
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status BOOLEAN DEFAULT TRUE,
    address_id INT,
    user_email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_email) REFERENCES users(email)
);


CREATE TABLE order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    price_id INT,
    product_id INT,
    quantity INT,
    ordered_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_date TIMESTAMP DEFAULT (CURRENT_TIMESTAMP + INTERVAL 7 DAY + INTERVAL FLOOR(RAND() * 4) HOUR + INTERVAL FLOOR(RAND() * 60) MINUTE),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (price_id) REFERENCES prices(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

 CREATE TABLE IF NOT EXISTS reviews(
  id INT AUTO_INCREMENT PRIMARY KEY,
  order_item_id INT,
  product_id INT,
  customer_name VARCHAR(220) NOT NULL ,
  ratings INT,
  review_message TEXT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     FOREIGN KEY (order_item_id) REFERENCES order_items(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
 );
 
  
  CREATE TABLE IF NOT EXISTS stocks(
   id INT AUTO_INCREMENT PRIMARY KEY,
     product_id INT,
     total_stock INT,
     status BOOLEAN DEFAULT TRUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
  );
 