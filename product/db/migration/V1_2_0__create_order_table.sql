use product_db;
CREATE TABLE IF NOT EXISTS purchase_order(
    id INT AUTO_INCREMENT,
    creation_date DATETIME NOT NULL,
    PRIMARY KEY(id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS purchase_order_product(
    purchase_order_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id),
    FOREIGN KEY (product_id) REFERENCES product(id))
ENGINE = InnoDB;