
-- Innitial script to create table and initial data
CREATE TABLE cities (
	id SERIAL PRIMARY KEY,
	city VARCHAR(128), 
	state VARCHAR(128)
);

-- Initial data to table 
INSERT INTO cities(city, state) VALUES('Santiago de Querétaro', 'Querétaro');
INSERT INTO cities(city, state) VALUES('San Juan del Río', 'Querétaro');
INSERT INTO cities(city, state) VALUES('Tequisquiapan', 'Querétaro');
INSERT INTO cities(city, state) VALUES('Guadalajara', 'Jalisco');
INSERT INTO cities(city, state) VALUES('Huichapan', 'Hidalgo');
INSERT INTO cities(city, state) VALUES('Pachuca', 'Hidalgo');
INSERT INTO cities(city, state) VALUES('Actopan', 'Hidalgo');
INSERT INTO cities(city, state) VALUES('Mérida', 'Yucatán');
INSERT INTO cities(city, state) VALUES('Acapulco', 'Guerrero');
INSERT INTO cities(city, state) VALUES('Mazatlán', 'Sinaloa');
INSERT INTO cities(city, state) VALUES('Puebla de Zaragoza', 'Puebla');
INSERT INTO cities(city, state) VALUES('Cholula', 'Puebla');
INSERT INTO cities(city, state) VALUES('Zacatlán', 'Puebla');
INSERT INTO cities(city, state) VALUES('Xalapa', 'Veracruz');
INSERT INTO cities(city, state) VALUES('Morelia', 'Michoacán');


-- Procedure that returns a single result set (cursor)
   CREATE OR REPLACE FUNCTION show_cities() RETURNS refcursor AS $$
    DECLARE
      ref refcursor;                                                     -- Declare a cursor variable
    BEGIN
      OPEN ref FOR SELECT city, state FROM cities;   -- Open a cursor
      RETURN ref;                                                       -- Return the cursor to the caller
    END;
    $$ LANGUAGE plpgsql;