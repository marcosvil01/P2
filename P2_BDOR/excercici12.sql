-- Consultem el títol i l'autor dels llibres de la taula 'Biblioteca' amb ISBN que comenci per '978'
SELECT l.llibre.titol, l.llibre.autor
FROM Biblioteca l
WHERE l.llibre.isbn LIKE '978%';
