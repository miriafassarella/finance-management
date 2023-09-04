CREATE TABLE person(
id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(50) NOT NULL,
        number VARCHAR(30),
        complement VARCHAR(30),
        district VARCHAR(30),
        zipCode VARCHAR(30),
        city VARCHAR(30),
        state VARCHAR(30),
        active BOOLEAN NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person(name, number, complement, district, zipCode, city, state, active)
VALUES ('Miria', '201', 'house', 'Mont Bellevue', 'J1H 6P2', 'sherbrooke', 'Québec', false);

