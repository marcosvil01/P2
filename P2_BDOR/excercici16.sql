-- Consulta per obtenir les ciutats úniques de la taula 'Persones'
SELECT DISTINCT ciutat
FROM Persones;

-- Consulta per obtenir els noms de les persones que comencen amb la lletra 'M'
SELECT nom
FROM Persones
WHERE nom LIKE 'M%';
