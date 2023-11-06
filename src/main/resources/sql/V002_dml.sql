-- Initial data to table 
INSERT INTO Cities(city, state) VALUES('Santiago de Querétaro', 'Querétaro');
INSERT INTO Cities(city, state) VALUES('San Juan del Río', 'Querétaro');
INSERT INTO Cities(city, state) VALUES('Tequisquiapan', 'Querétaro');
INSERT INTO Cities(city, state) VALUES('Guadalajara', 'Jalisco');
INSERT INTO Cities(city, state) VALUES('Huichapan', 'Hidalgo');
INSERT INTO Cities(city, state) VALUES('Pachuca', 'Hidalgo');
INSERT INTO Cities(city, state) VALUES('Actopan', 'Hidalgo');
INSERT INTO Cities(city, state) VALUES('Mérida', 'Yucatán');
INSERT INTO Cities(city, state) VALUES('Acapulco', 'Guerrero');
INSERT INTO Cities(city, state) VALUES('Mazatlán', 'Sinaloa');
INSERT INTO Cities(city, state) VALUES('Puebla de Zaragoza', 'Puebla');
INSERT INTO Cities(city, state) VALUES('Cholula', 'Puebla');
INSERT INTO Cities(city, state) VALUES('Zacatlán', 'Puebla');
INSERT INTO Cities(city, state) VALUES('Xalapa', 'Veracruz');
INSERT INTO Cities(city, state) VALUES('Morelia', 'Michoacán');

/*
 * Default user:
 * 	      username: quiron
 * 	  password_raw: esteganografia_01+
 * password_bcrypt: $2a$10$b1p96SYC4NAr6uUBVgCXSu9v6qFX0Qc/aPSKD03pLNGT/hoN7KtUu
 */
INSERT INTO users(fullname, email, username, passwd, is_Active) VALUES
	('Alejandro Bravo', 'bravotorres.alejandro@gmail.com', 'quiron', '$2a$10$b1p96SYC4NAr6uUBVgCXSu9v6qFX0Qc/aPSKD03pLNGT/hoN7KtUu', 1);
