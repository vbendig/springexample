use product_db;
CREATE TABLE IF NOT EXISTS product(
    id INT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(256) NOT NULL,
    PRIMARY KEY(id))
ENGINE = InnoDB;