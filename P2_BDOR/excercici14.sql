-- Creem un tipus de dades que pot emmagatzemar fins a 5 telèfons
CREATE OR REPLACE TYPE TelArray AS VARRAY(5) OF VARCHAR2(15);

-- Creem la taula 'Clients' que conté una columna per a telèfons que utilitza el tipus de dades 'TelArray'
CREATE TABLE Clients (
    id NUMBER PRIMARY KEY,   -- ID del client
    nom VARCHAR2(100),       -- Nom del client
    telefons TelArray        -- Columna que emmagatzema una llista de telèfons
);

-- Insertem un registre a la taula 'Clients' amb tres números de telèfon
INSERT INTO Clients (id, nom, telefons)
VALUES (1, 'Joan', TelArray('123456789', '987654321', '456123789'));
