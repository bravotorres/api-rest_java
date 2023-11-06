SET SERVEROUTPUT ON;
CREATE OR REPLACE PACKAGE PKG_LABS01 IS
    
    PROCEDURE CITY_CREATE (
        name IN VARCHAR2,
        state IN VARCHAR2,
        
        -- All SPs contains all same output params:
        response OUT CURSOR,  -- Is a Integer with CityID of a City inserted
        code OUT NUMBER,
        message OUT VARCHAR2
    );

    PROCEDURE CITY_READ_ONE (
        c_id IN NUMBER,

        -- All SPs contains all same output params:
        response OUT SYS_REFCURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    );

    PROCEDURE CITY_READ_ALL (
        -- All SPs contains all same output params:
        response OUT SYS_REFCURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    );

    PROCEDURE CITY_UPDATE (
        c_id IN NUMBER,  -- CityID to DataUpdate
        name IN VARCHAR2,
        state IN VARCHAR2,
        
        -- All SPs contains all same output params:
        response OUT CURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    );
    
    PROCEDURE CITY_DELETE (
        c_id IN NUMBER,  -- CityID to Remove
        
        -- All SPs contains all same output params:
        response OUT CURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    );
    

END PKG_LABS01;

/ 

CREATE OR REPLACE PACKAGE BODY PKG_LABS01 IS
    PROCEDURE CITY_CREATE (
        c_name IN VARCHAR2,
        c_state IN VARCHAR2,
        
        -- All SPs contains all same output params:
        response OUT CURSOR,  -- Is a Integer with CityID of a City inserted
        code OUT NUMBER,
        message OUT VARCHAR2
    ) AS
        aux NUMBER := 0;
    BEGIN
        DBMS_OUTPUT.enable();
        
        INSERT INTO Cities(name, state) VALUES() COUNT(c.city) INTO aux FROM Cities c WHERE c.id = c_id;
        
        IF aux != 0 THEN
            OPEN response FOR SELECT c.id, c.city, c.state FROM Cities c WHERE c.id = c_id;
            code := 0;
            message := 'Consulta correcta.';
        ELSE
            code := 1;
            message := 'Consulta sin resultados.';
        END IF;
        
    EXCEPTION
        WHEN OTHERS THEN
            code := 1;
            message := 'Error en consulta: ' || SQLERRM;
            DBMS_OUTPUT.PUT_LINE(message);
    END;

    PROCEDURE CITY_READ_ONE (
        c_id IN NUMBER,
        response OUT SYS_REFCURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    ) AS
        aux NUMBER := 0;
    BEGIN
        DBMS_OUTPUT.enable();
        
        SELECT COUNT(c.city) INTO aux FROM Cities c WHERE c.id = c_id;
        
        IF aux != 0 THEN
            OPEN response FOR SELECT c.id, c.city, c.state FROM Cities c WHERE c.id = c_id;
            code := 0;
            message := 'Consulta correcta.';
        ELSE
            code := 1;
            message := 'Consulta sin resultados.';
        END IF;
        
    EXCEPTION
        WHEN OTHERS THEN
            code := 1;
            message := 'Error en consulta: ' || SQLERRM;
            DBMS_OUTPUT.PUT_LINE(message);
    END;

    PROCEDURE CITY_READ_ALL (
        response OUT SYS_REFCURSOR,
        code OUT NUMBER,
        message OUT VARCHAR2
    ) AS
        aux NUMBER := 0;
    BEGIN
        DBMS_OUTPUT.enable();
        
        SELECT COUNT(c.city) INTO aux FROM Cities c WHERE c.id = c_id;
        
        IF aux != 0 THEN
            OPEN response FOR SELECT c.id, c.city, c.state FROM Cities c;
            code := 0;
            message := 'Consulta correcta.';
        ELSE
            code := 1;
            message := 'Consulta sin resultados.';
        END IF;
        
    EXCEPTION
        WHEN OTHERS THEN
            code := 1;
            message := 'Error en consulta: ' || SQLERRM;
            DBMS_OUTPUT.PUT_LINE(message);
    END;

END PKG_LABS01;
