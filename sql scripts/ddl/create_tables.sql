CREATE TABLE customer
  (
     customer_id INT PRIMARY KEY NOT NULL,
     first_name VARCHAR(30) NOT NULL,
     last_name VARCHAR(30) NOT NULL,
     email VARCHAR(30) NOT NULL,
     street VARCHAR(30) NOT NULL,
     house VARCHAR(30) NOT NULL,
     apartment VARCHAR(30) NOT NULL,
     card_number VARCHAR(20),
     card_exp_data TIMESTAMP,
     balance INT
  );

CREATE TABLE phone_number
  (
     phone_number VARCHAR(20) PRIMARY KEY,
     customer_id INT NOT NULL,
     start_date TIMESTAMP,
     end_date TIMESTAMP,
     comment_string VARCHAR(100)
  );

CREATE TABLE markets_customers
  (
     market_id INT PRIMARY KEY,
     customer_id INT,
     start_date TIMESTAMP,
     end_date TIMESTAMP
  );

CREATE TABLE market
  (
     market_id INT PRIMARY KEY,
     name VARCHAR(30),
     description VARCHAR(30)
  );

CREATE TABLE market_products
  (
     market_id INT PRIMARY KEY,
     product_id INT,
     price INT,
     start_date TIMESTAMP,
     end_date TIMESTAMP
  );

CREATE TABLE product
  (
     product_id INT PRIMARY KEY,
     name VARCHAR (30),
     description VARCHAR(30),
     sales_period TIMESTAMP,
     baseline_price INT
  );

CREATE TABLE customer_products
  (
     customer_id INTEGER PRIMARY KEY,
     product_id INTEGER,
     start_date TIMESTAMP,
     end_date TIMESTAMP,
     price INTEGER
  );

CREATE TABLE orders
  (
     order_id INTEGER PRIMARY KEY,
     order_date TIMESTAMP,
     order_type VARCHAR(30),
     type_comment VARCHAR(30),
     status VARCHAR(30),
     priority VARCHAR(30),
     customer_id INT,
     manager_id INT,
     technical_support_flag VARCHAR(30),
     product_id INTEGER
  );

CREATE TABLE order_processing
  (
     order_id INT PRIMARY KEY,
     step_name VARCHAR(30),
     description VARCHAR(30),
     start_date TIMESTAMP,
     end_date TIMESTAMP,
     end_date_hard TIMESTAMP,
     employee_id INT,
     equipment_id INT
  );

CREATE TABLE employee
  (
     employee_id INT PRIMARY KEY,
     job_description VARCHAR(30),
     first_name VARCHAR(30),
     last_name VARCHAR(30),
     schedule TIMESTAMP
  );

CREATE TABLE equipment
  (
     equipment_id INT PRIMARY KEY,
     customer_id INT,
     name VARCHAR(30),
     serial_number INT,
     description VARCHAR(30),
     status VARCHAR(30)
  );

CREATE TABLE equipment_history
  (
     equipment_id INT PRIMARY KEY,
     customer_id INT,
     start_date TIMESTAMP,
     end_date TIMESTAMP,
     equipment_comment VARCHAR(30),
     status VARCHAR(30)
  );

ALTER TABLE phone_number 
  ADD CONSTRAINT order_cust_id  FOREIGN KEY (customer_id) REFERENCES customer (customer_id);

ALTER TABLE markets_customers
  ADD CONSTRAINT markets_customers_market_id FOREIGN KEY (market_id) REFERENCES market (market_id);

ALTER TABLE market
  ADD CONSTRAINT market_market_id FOREIGN KEY (market_id) REFERENCES markets_customers (market_id);

ALTER TABLE markets_customers
  ADD CONSTRAINT market_customer_cust_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id);

ALTER TABLE market_products
  ADD CONSTRAINT market_product_market_id FOREIGN KEY (market_id) REFERENCES market (market_id);

ALTER TABLE market_products
  ADD CONSTRAINT market_product_product_id FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE customer_products
  ADD CONSTRAINT customer_product_product_id FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE orders
  ADD employee_id INT;

ALTER TABLE orders
  ADD CONSTRAINT orders_product_id FOREIGN KEY (product_id) REFERENCES product(product_id);

ALTER TABLE orders
  ADD CONSTRAINT orders_cust_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id);

ALTER TABLE orders
  ADD CONSTRAINT orders_order_id FOREIGN KEY (order_id) REFERENCES order_processing(order_id);

ALTER TABLE order_processing
  ADD CONSTRAINT orderd_processing_order_id FOREIGN KEY (order_id) REFERENCES orders (order_id);

ALTER TABLE orders
  ADD CONSTRAINT orders_employee_id FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

ALTER TABLE order_processing
  ADD CONSTRAINT orders_processing_empl_id FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

ALTER TABLE order_processing
  ADD CONSTRAINT order_processing_equipment_id FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id);

ALTER TABLE equipment_history
  ADD CONSTRAINT equipment_history_equipment_id FOREIGN KEY (equipment_id) REFERENCES equipment(equipment_id); 
  
  
