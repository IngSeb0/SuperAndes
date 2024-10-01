CREATE TABLE InfoExtraOrden (
                              
    PRODUCTO_codigoBarras VARCHAR2(100) NOT NULL,
    ORDENCOMPRA_idOrden INT NOT NULL,              
    cantidad INT NOT NULL,                                       
    costoUnitario FLOAT NOT NULL CHECK (costoUnitario > 0),    

    PRIMARY KEY (ORDENCOMPRA_idOrden, PRODUCTO_codigoBarras),  

    CONSTRAINT fk_Ordenid
        FOREIGN KEY (ORDENCOMPRA_idOrden) 
        REFERENCES OrdenCompra(idOrden),                        

    CONSTRAINT fk_ProductoId
        FOREIGN KEY (PRODUCTO_codigoBarras) 
        REFERENCES Producto(codigoBarras)                      
);
