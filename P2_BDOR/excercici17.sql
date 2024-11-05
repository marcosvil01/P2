-- Creem una taula 'Dispositius' amb una columna per emmagatzemar l'estat del dispositiu (1 = encès, 0 = apagat)
CREATE TABLE Dispositius (
    id NUMBER PRIMARY KEY,   -- ID del dispositiu
    estat NUMBER(1)          -- Estat del dispositiu (1 per encès, 0 per apagat)
);

-- Inserim exemples a la taula 'Dispositius'
INSERT INTO Dispositius (id, estat) VALUES (1, 1); -- Dispositiu encès
INSERT INTO Dispositius (id, estat) VALUES (2, 0); -- Dispositiu apagat
