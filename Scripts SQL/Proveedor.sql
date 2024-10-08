CREATE TABLE Proveedor (
    NIT INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,  
    nombreProveedor VARCHAR2(100) NOT NULL,                
    direccion VARCHAR2(100) NOT NULL,                      
    nombreContacto VARCHAR2(100) NOT NULL,                 
    telefonoContacto VARCHAR2(100) NOT NULL,                                                        
    CONSTRAINT chk_telefono CHECK (telefonoContacto = 10)          
);