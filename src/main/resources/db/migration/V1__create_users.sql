 * CREATE database if not exists Ecommerse_FAB;
   use Ecommerse_FAB;
  CREATE TABLE if not exists users (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(50) NOT NULL,
      email VARCHAR(50) NOT NULL,
      password VARCHAR(20) NOT NULL,
      mobile_number LONG NOT NULL,
      is_active BOOLEAN DEFAULT TRUE,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE     CURRENT_TIMESTAMP
  );


* CREATE TABLE if not exists categories_type (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  );



  * CREATE TABLE IF NOT EXISTS categories (
      id INT AUTO_INCREMENT PRIMARY KEY,
      name VARCHAR(150) NOT NULL,
      category_type_id INT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (category_type_id) REFERENCES categories_type(id)
  );


  * CREATE TABLE IF NOT EXISTS products(
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  product_weight VARCHAR(20) NOT NULL,
  description TEXT NOT NULL,
  benefits TEXT NOT NULL,
  application TEXT NOT NULL,
  category_id INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (category_id) REFERENCES categories(id)
  );


  * CREATE TABLE IF NOT EXISTS prices(
      id INT AUTO_INCREMENT PRIMARY KEY,
      price INT NOT NULL,
      start_date TIMESTAMP NOT NULL,
      end_date TIMESTAMP,
      product_id INT,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      FOREIGN KEY (product_id) REFERENCES products(id)
  );