-- Creem un tipus d'objecte anomenat 'Llibre' amb tres atributs: titol, autor, i isbn
CREATE OR REPLACE TYPE Llibre AS OBJECT (
    titol VARCHAR2(100),   -- Títol del llibre
    autor VARCHAR2(100),   -- Autor del llibre
    isbn VARCHAR2(20)      -- ISBN del llibre
);
