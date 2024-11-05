-- Creem un tipus d'objecte 'Persona' amb els atributs nom i cognom
CREATE OR REPLACE TYPE Persona AS OBJECT (
    nom VARCHAR2(50),    -- Nom de la persona
    cognom VARCHAR2(50)  -- Cognom de la persona
);

-- Creem un tipus 'Professor' que hereta de 'Persona' i afegeix l'atribut 'departament'
CREATE OR REPLACE TYPE Professor UNDER Persona (
    departament VARCHAR2(50)  -- Departament del professor
);
