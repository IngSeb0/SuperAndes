CREATE TABLE infoExtraVenta (
    cantidad INT NOT NULL,                             
    precioUnitario NUMBER NOT NULL,                    

    Venta_idVenta INT NOT NULL,                       
    Producto_codigoBarras VARCHAR2(100) NOT NULL,      


    CONSTRAINT chk_precioUnitario CHECK (precioUnitario > 0),


    PRIMARY KEY (Venta_idVenta, Producto_codigoBarras),


    CONSTRAINT FK_INFOEXTRA_VENTA FOREIGN KEY (Venta_idVenta) 
        REFERENCES Venta(idVenta),  
        
    CONSTRAINT FK_INFOEXTRA_PRODUCTO FOREIGN KEY (Producto_codigoBarras) 
        REFERENCES Producto(codigoBarras)  
);
