CREATE TABLE especificacionEmpacado (
    idEspecificacion INT GENERATED ALWAYS AS IDENTITY,  
    volumen FLOAT NOT NULL,                               
    peso FLOAT NOT NULL,                                  
    CONSTRAINT chk_volumen CHECK (volumen > 0),        
    CONSTRAINT chk_peso CHECK (peso > 0),               
    CONSTRAINT pk_idEspecificacion PRIMARY KEY (idEspecificacion)  
);