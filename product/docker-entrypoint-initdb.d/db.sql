CREATE DATABASE IF NOT EXISTS product_db;
CREATE USER IF NOT EXISTS 'productAdmin'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON product_db.* TO 'productAdmin'@'%';

CREATE USER IF NOT EXISTS 'productUser'@'%' IDENTIFIED BY 'password';
GRANT CREATE, SELECT, INSERT, UPDATE, DELETE ON product_db.* TO 'productUser'@'%';

CREATE USER IF NOT EXISTS 'flyway'@'%' IDENTIFIED BY 'falafly';
CREATE USER IF NOT EXISTS 'flywayDEL'@'%' IDENTIFIED BY 'falafly';

GRANT DELETE, DROP ON product_db.* TO 'flywayDEL'@'%';
GRANT CREATE, SELECT, INSERT, UPDATE, ALTER, REFERENCES ON product_db.* TO 'flyway'@'%';
FLUSH PRIVILEGES;