CREATE TABLE detalleCostoBodega (
    idDetalleCosto INT PRIMARY KEY NOT NULL,                 
    costoUnitarioBodega FLOAT NOT NULL CHECK (costoUnitarioBodega > 0), 
    cantidadExistencia INT NOT NULL,                        
    BODEGA_idBodega INT,                                           
    PRODUCTO_codigoBarras VARCHAR2(100),                            

    CONSTRAINT FK_BODEGA FOREIGN KEY (BODEGA_idBodega) 
        REFERENCES Bodega(idBodega),                       

    CONSTRAINT FK_PRODUCTO FOREIGN KEY (PRODUCTO_codigoBarras) 
        REFERENCES Producto(codigoBarras)                  
);
