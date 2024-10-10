CREATE TABLE ProductoPerecedero (
    PRODUCTO_codigoBarras VARCHAR2(100) PRIMARY KEY,                   
    fechaVencimiento DATE NOT NULL,                           
    CONSTRAINT chk_fechaVencimiento CHECK (fechaVencimiento > SYSDATE),  
    CONSTRAINT PRODUCTOPERECEDERO_PRODUCTO_FK FOREIGN KEY (PRODUCTO_codigoBarras) 
        REFERENCES Producto(codigoBarras)                     
);
