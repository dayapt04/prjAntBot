-- database: ../database/EXOBOT.sqlite

insert INTO IA (Nombre   ,Estado ) 
            VALUES("IA-RUSO" ,"" );

SELECT * FROM IA;

INSERT INTO AntBot(
 IdIA   
,Serie    
,Estado     
) VALUES(
 1
,"Serie H1"
,'A'
);

INSERT INTO AntBot(
 IdIA   
,Serie     
,Estado    
) VALUES(
 1
,"Serie H2"
,'A'
);  

SELECT * FROM AntBot;

INSERT INTO HormigaClasificacion(
CodigoUnico
,Estado
) VALUES(
"HMG101"
,'A'
);

INSERT INTO HormigaClasificacion(
CodigoUnico
,Estado
) VALUES(
"HMG102"
,'A'
);

INSERT INTO HormigaClasificacion(
CodigoUnico
,Estado
) VALUES(
"HMG103"
,'A'
);

INSERT INTO HormigaClasificacion(
CodigoUnico
,Estado
) VALUES(
"HMG104"
,'A'
);

INSERT INTO HormigaClasificacion(
CodigoUnico
,Estado
) VALUES(
"HMG105"
,'A'
);

SELECT * FROM HormigaClasificacion;

INSERT INTO Hormiga(
IdHormigaClasificacion
,Nombre
,Estado
) VALUES(
"HMG101"
,"Reina"
,'A'
);

INSERT INTO Hormiga(
IdHormigaClasificacion
,Nombre
,Estado
) VALUES(
"HMG102"
,"Zangano"
,'A'
);

INSERT INTO Hormiga(
IdHormigaClasificacion
,Nombre
,Estado
) VALUES(
"HMG103"
,"Soldado"
,'A'
);

INSERT INTO Hormiga(
IdHormigaClasificacion
,Nombre
,Estado
) VALUES(
"HMG104"
,"Explorador"
,'A'
);

INSERT INTO Hormiga(
IdHormigaClasificacion
,Nombre
,Estado
) VALUES(
"HMG105"
,"Larva"
,'A'
);

--UPDATE Hormiga SET Nombre = "CAMBIO" WHERE IdHormiga = 6

SELECT * FROM Hormiga;

SELECT  IdHormiga, Nombre
FROM    Hormiga
WHERE   Estado ='A' AND IdHormiga = 2;

SELECT  count(*)
FROM    Hormiga
WHERE   Estado ='A';