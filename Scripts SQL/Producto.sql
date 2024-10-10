CREATE TABLE Producto (
    codigoBarras VARCHAR2(100) NOT NULL,                       
    nombreProducto VARCHAR2(100) NOT NULL,                    
    precioUnitario FLOAT NOT NULL,                             
    presentacion VARCHAR2(100) NOT NULL,                      
    cantidadPresentacion INT NOT NULL,                         
    unidadMedida VARCHAR2(100) NOT NULL,                      
    fechaExpiracion DATE NOT NULL,                            
    ESPECIFICADOEMPACADO_IDESPECIFICACION INT,                
    CONSTRAINT chk_precio CHECK (precioUnitario > 0),       
    CONSTRAINT chk_fecha CHECK (fechaExpiracion > SYSDATE),  
    CONSTRAINT pk_codigoBarras PRIMARY KEY (codigoBarras),    
    CONSTRAINT FK_ESPECIFICADOEMPACADO
        FOREIGN KEY (ESPECIFICADOEMPACADO_IDESPECIFICACION) 
        REFERENCES EspecificadoEmpacado(IDESPECIFICACION)     
);
