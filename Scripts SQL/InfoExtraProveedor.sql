CREATE TABLE InfoExtraProveedor (
    PROVEEDOR_NIT INT NOT NULL,                               
    PRODUCTO_codigoBarras VARCHAR2(100) NOT NULL,           
    cantidadExistencias INT NOT NULL,                         

    PRIMARY KEY (PROVEEDOR_NIT, PRODUCTO_codigoBarras),     

    CONSTRAINT fk_proveedor
        FOREIGN KEY (PROVEEDOR_NIT) 
        REFERENCES Proveedor(NIT),                         
    CONSTRAINT fk_Producto
        FOREIGN KEY (PRODUCTO_codigoBarras) 
        REFERENCES Producto(codigoBarras)                   
);
