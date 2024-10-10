CREATE TABLE Ciudad (
    codigoCiudad INT GENERATED ALWAYS AS IDENTITY,  
    nombreCiudad VARCHAR2(100) NOT NULL,            
    CONSTRAINT pk_codigoCiudad PRIMARY KEY (codigoCiudad) 
);
