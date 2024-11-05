-- Creem un tipus d'objecte 'Vehicle' amb dos atributs: marca i model
CREATE OR REPLACE TYPE Vehicle AS OBJECT (
    marca VARCHAR2(50),  -- Marca del vehicle
    model VARCHAR2(50)   -- Model del vehicle
);

-- Creem un tipus 'Cotxe' que hereta de 'Vehicle' i afegeix l'atribut 'nombre_de_portes'
CREATE OR REPLACE TYPE Cotxe UNDER Vehicle (
    nombre_de_portes NUMBER  -- Nombre de portes del cotxe
);
