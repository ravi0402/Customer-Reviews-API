CREATE TABLE IF NOT EXISTS `Comments` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `comment_text` varchar(200),
	`review_id` int NOT NULL,
	FOREIGN KEY(review_id)
    REFERENCES Review (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;