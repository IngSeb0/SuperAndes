CREATE TABLE sucursal (
    idSucursal INT GENERATED ALWAYS AS IDENTITY,   
    nombreSucursal VARCHAR2(100) NOT NULL,         
    tamanioInstalacion FLOAT NOT NULL,              
    direccion VARCHAR2(100) NOT NULL,               
    telefono VARCHAR2(100) NOT NULL,                
    CIUDAD_codigoCiudad INT,                                
    CONSTRAINT fk_codigoCiudad 
        FOREIGN KEY (CIUDAD_codigoCiudad) 
        REFERENCES Ciudad(codigoCiudad),            
    CONSTRAINT chk_tamanioInstalacion CHECK (tamanioInstalacion > 0),  
    CONSTRAINT chk_telefono CHECK (LENGTH(telefono) >= 10),  
    CONSTRAINT pk_idSucursal PRIMARY KEY (idSucursal)  
);
