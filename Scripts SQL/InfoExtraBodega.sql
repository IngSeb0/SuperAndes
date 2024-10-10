CREATE TABLE infoExtraBodega (
    totalExistencias INT NOT NULL,                              
    costoPromedio FLOAT NOT NULL CHECK (costoPromedio > 0),   
    capacidadAlmacenamiento INT NOT NULL CHECK (capacidadAlmacenamiento > 0),  
    nivelMinimo FLOAT NOT NULL CHECK (nivelMinimo > 0),        
    PRODUCTO_codigoBarras VARCHAR2(100),                                
    BODEGA_idBodega INT,                                      

    PRIMARY KEY (codigoBarras, BODEGA_idBodega),             

    CONSTRAINT FK_infoExtraBodega_Producto 
        FOREIGN KEY (PRODUCTO_codigoBarras) 
        REFERENCES Producto(codigoBarras),                      

    CONSTRAINT FK_infoExtraBodega_BODEGA 
        FOREIGN KEY (BODEGA_idBodega) 
        REFERENCES Bodega(idBodega)                             
);
