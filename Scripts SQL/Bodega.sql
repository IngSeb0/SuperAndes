CREATE TABLE Bodega (
    idBodega INT GENERATED ALWAYS AS IDENTITY,  
    nombreBodega VARCHAR2(100) NOT NULL,        
    tamanioBodega FLOAT NOT NULL,               
    SUCURSAL_idSucursal INT,                    
    CONSTRAINT fk_sucursal
        FOREIGN KEY (SUCURSAL_idSucursal) 
        REFERENCES Sucursal(idSucursal),        
    CONSTRAINT chk_idBodega_pos CHECK (idBodega > 0),  
    PRIMARY KEY (idBodega)                      
);
