package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INFOEXTRAPROVEEDOR")
public class InfoExtraProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfoExtraProveedor;
    
    private Integer cantidadExistencias;
    private Long proveedorNit;
    private String productoCodigoBarras;

    public InfoExtraProveedor() {}

    public InfoExtraProveedor(Integer cantidadExistencias, Long proveedorNit, String productoCodigoBarras) {
        this.cantidadExistencias = cantidadExistencias;
        this.proveedorNit = proveedorNit;
        this.productoCodigoBarras = productoCodigoBarras;
    }

    public Long getIdInfoExtraProveedor() {
        return idInfoExtraProveedor;
    }

    public void setIdInfoExtraProveedor(Long idInfoExtraProveedor) {
        this.idInfoExtraProveedor = idInfoExtraProveedor;
    }

    public Integer getCantidadExistencias() {
        return cantidadExistencias;
    }

    public void setCantidadExistencias(Integer cantidadExistencias) {
        this.cantidadExistencias = cantidadExistencias;
    }

    public Long getProveedorNit() {
        return proveedorNit;
    }

    public void setProveedorNit(Long proveedorNit) {
        this.proveedorNit = proveedorNit;
    }

    public String getProductoCodigoBarras() {
        return productoCodigoBarras;
    }

    public void setProductoCodigoBarras(String productoCodigoBarras) {
        this.productoCodigoBarras = productoCodigoBarras;
    }

    @Override
    public String toString() {
        return "InfoExtraProveedor{" +
                "idInfoExtraProveedor=" + idInfoExtraProveedor +
                ", cantidadExistencias=" + cantidadExistencias +
                ", proveedorNit=" + proveedorNit +
                ", productoCodigoBarras='" + productoCodigoBarras + '\'' +
                '}';
    }
}
