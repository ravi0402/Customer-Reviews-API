CREATE TABLE IF NOT EXISTS `Review` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `review_text` varchar(200),
	`product_id` int NOT NULL,
	FOREIGN KEY(product_id)
    REFERENCES Product (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;