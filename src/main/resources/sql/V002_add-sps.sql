
-- Procedure that returns a single result set (cursor)
   CREATE OR REPLACE FUNCTION show_cities() RETURNS refcursor AS $$
    DECLARE
      ref refcursor;                                                     -- Declare a cursor variable
    BEGIN
      OPEN ref FOR SELECT city, state FROM cities;   -- Open a cursor
      RETURN ref;                                                       -- Return the cursor to the caller
    END;
    $$ LANGUAGE plpgsql;
 





CREATE OR REPLACE FUNCTION login_info(_username varchar, _password varchar, ref out refcursor) AS $$
--declare
--	ref refcursor;                                -- Declare a cursor variable
begin
	OPEN ref FOR (
		select u.fullname, u.email from users as u
		where
			u.username = _username and
			u.passwd = crypt(_password, u.passwd) and
			u.is_active = true);
END;
$$ LANGUAGE plpgsql;


SELECT login_info('quiron', 'admin');

select u.fullname, u.email from users as u where ;










-- ...
create table cities (
	id serial primary key,
	city varchar(128),
	state varchar(128)
);

insert into cities(city, state) values('Santiago de Querétaro', 'Querétaro');
insert into cities(city, state) values('San Juan del Río', 'Querétaro');
insert into cities(city, state) values('Tequisquiapan', 'Querétaro');
insert into cities(city, state) values('Guadalajara', 'Jalisco');
insert into cities(city, state) values('Huichapan', 'Hidalgo');
insert into cities(city, state) values('Pachuca', 'Hidalgo');
insert into cities(city, state) values('Actopan', 'Hidalgo');
insert into cities(city, state) values('Mérida', 'Yucatán');
insert into cities(city, state) values('Acapulco', 'Guerrero');
insert into cities(city, state) values('Mazatlán', 'Sinaloa');
insert into cities(city, state) values('Puebla de Zaragoza', 'Puebla');
insert into cities(city, state) values('Cholula', 'Puebla');
insert into cities(city, state) values('Zacatlán', 'Puebla');
insert into cities(city, state) values('Xalapa', 'Veracruz');
insert into cities(city, state) values('Morelia', 'Michoacán');

select * from cities order by id;

-- Procedure that returns a single result set (cursor)
CREATE OR REPLACE PROCEDURE show_cities() RETURNS refcursor AS $$
declare
	ref refcursor;                                -- Declare a cursor variable
BEGIN
	OPEN ref FOR SELECT city, state FROM cities;  -- Open a cursor
	RETURN ref;                                   -- Return the cursor to the caller
END;
$$ LANGUAGE plpgsql;



-- Create a new City, return ID
CREATE OR REPLACE PROCEDURE add_city(
	c_name varchar(128),
	c_state varchar(128),
	c_id inout int
)
LANGUAGE plpgsql as
$$
begin
	insert into cities(city, state) values(c_name, c_state) returning id into c_id;
end
$$;

-- TEST Create
CALL add_city('Mexicali', 'Baja California', null);



-- Read City by ID
CREATE OR REPLACE PROCEDURE read_city_one(
	c_id int
)
LANGUAGE plpgsql as $$
begin
	update cities set
		city = c_name,
		state = c_state
	where
		id = c_id;
end $$;


-- Read All Cities
/*
 * Cursor:
 * 	declare
 * 	open
 * 	fetch
 * 	if is all, close, else, fetch again.
 */
CREATE OR REPLACE PROCEDURE read_city_all(
	cities_cur inout refcursor
)
LANGUAGE plpgsql as $$
begin
	open cities_cur for
		select * from cities order by 1;
	close cities_cur;
end $$;

-- drop procedure read_city_all(refcursor));

begin
	call read_city_all('all_cities');
	fetch all in 'all_cities';
commit;





select * from cities order by 1;

-- Update City by ID
CREATE OR REPLACE PROCEDURE update_city(
	c_name varchar(128),
	c_state varchar(128),
	c_id int
)
LANGUAGE plpgsql as $$
begin
	update cities set
		city = c_name,
		state = c_state
	where
		id = c_id;
end $$;

-- TEST Update
CALL update_city('Mexxxxicali', 'Baja California', 16);

select * from cities;

-- Delete City by ID
CREATE OR REPLACE PROCEDURE delete_city(c_id int)
LANGUAGE plpgsql as $$
begin
	delete from cities where id = c_id;
end $$;

-- TEST Delete
CALL delete_city(16);

select * from cities;


-- Start a transaction
BEGIN;
	SELECT show_cities();  -- Returns: <unnamed portal 2>
	FETCH ALL IN "<unnamed portal 2>";
COMMIT;


drop function show_cities() ;



