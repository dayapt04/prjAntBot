-- database: ../database/ANTBOT.sqlite

DROP TABLE IF EXISTS IA;

CREATE TABLE IA
     IA (
        IdIA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        Nombre VARCHAR(10) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCrea DATETIME DEFAULT (datetime('now', 'localtime')),
        FechaModifica DATETIME
    );

DROP TABLE IF EXISTS AntBot;

CREATE TABLE AntBot
     AntBot (
        IdAntBot INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        IdIA INTEGER REFERENCES IA(IdIA)
        Serie VARCHAR(10) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCrea DATETIME DEFAULT (datetime('now', 'localtime')),
        FechaModifica DATETIME
    );

DROP TABLE IF EXISTS HormigaClasificacion;

CREATE TABLE HormigaClasificacion
     HormigaClasificacion (
        IdHormigaClasificacion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        CodigoUnico VARCHAR(6) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCrea DATETIME DEFAULT (datetime('now', 'localtime')),
        FechaModifica DATETIME
    );

DROP TABLE IF EXISTS Hormiga;

CREATE TABLE Hormiga
     Hormiga (
        IdHormiga INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        IdHormigaClasificacion INTEGER REFERENCES HormigaClasificacion(IdHormigaClasificacion)
        Nombre VARCHAR(50) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCrea DATETIME DEFAULT (datetime('now', 'localtime')),
        FechaModifica DATETIME
    );