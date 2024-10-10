CREATE TABLE venta (
    idVenta INT PRIMARY KEY NOT NULL,                
    fecha DATE NOT NULL CHECK (fecha <= SYSDATE),                         
    cantidadExistencia INT NOT NULL,                 
    SUCURSAL_idSucursal INT,                          
    CLIENTE_cedula INT,                              
    PROVEEDOR_NIT INT,                               

    CONSTRAINT FK_VENTA_SUCURSAL FOREIGN KEY (SUCURSAL_idSucursal) 
        REFERENCES Sucursal(idSucursal),  
    
    CONSTRAINT FK_VENTA_CLIENTE FOREIGN KEY (CLIENTE_cedula) 
        REFERENCES Cliente(cedula),      

    CONSTRAINT FK_VENTA_PROVEEDOR FOREIGN KEY (PROVEEDOR_NIT) 
        REFERENCES Proveedor(NIT)                
);
