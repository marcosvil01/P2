-- Creem una taula 'Persones' per emmagatzemar informació de persones
CREATE TABLE Persones (
    id NUMBER PRIMARY KEY,   -- ID de la persona
    nom VARCHAR2(100),       -- Nom de la persona
    ciutat VARCHAR2(100)     -- Ciutat de la persona
);

-- Creem una taula 'LogTable' per registrar esdeveniments
CREATE TABLE LogTable (
    event VARCHAR2(50),       -- Tipus d'esdeveniment
    description VARCHAR2(200) -- Descripció de l'esdeveniment
);

-- Creem un trigger que s'executa després d'inserir una nova persona a la taula 'Persones'
CREATE OR REPLACE TRIGGER LogInsert
AFTER INSERT ON Persones
FOR EACH ROW
BEGIN
    -- Inserim un registre a la 'LogTable' per indicar que una nova persona ha estat afegida
    INSERT INTO LogTable (event, description)
    VALUES ('INSERT', 'Nova persona afegida amb ID: ' || :NEW.id);
END;
