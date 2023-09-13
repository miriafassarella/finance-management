CREATE TABLE entry(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	pay_day DATE NOT NULL,
	price DECIMAL(10,2) NOT NULL,
	observation VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	id_category BIGINT(20) NOT NULL,
	id_person BIGINT(20) NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entry(description, due_date, pay_day, price, observation, type, id_category, id_person)
VALUES ('Monthly salary', '2023-08-06', '2023-08-10', 100.32, 'Profit distribution', 'REVENUE', 2, 1);

INSERT INTO entry(description, due_date, pay_day, price, observation, type, id_category, id_person)
VALUES ('Coffee', '2023-08-06', '2023-08-15', 10.20, null, 'EXPENSE', 3, 1);