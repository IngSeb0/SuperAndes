CREATE TABLE RecepcionProductos (
    fechaRecepcion DATE NOT NULL CHECK (fechaRecepcion <= SYSDATE),  
    ORDENCOMPRA_idOrden INT NOT NULL,                          
    BODEGA_idBodega INT NOT NULL,                              
 

    PRIMARY KEY (ORDENCOMPRA_idOrden, BODEGA_idBodega),      

    CONSTRAINT fk_ordenCompra
        FOREIGN KEY (ORDENCOMPRA_idOrden) 
        REFERENCES OrdenCompra(idOrden),                       

    CONSTRAINT fk_bodega
        FOREIGN KEY (BODEGA_idBodega) 
        REFERENCES Bodega(idBodega)                            
);
