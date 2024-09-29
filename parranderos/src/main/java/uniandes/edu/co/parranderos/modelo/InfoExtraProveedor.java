package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "INFOEXTRAPROVEEDOR")
public class InfoExtraProveedor {

    @EmbeddedId
    private InfoExtraProveedorPk pk;

    private Integer cantidadExistencias;

    public InfoExtraProveedor() {
        super();
    }

    public InfoExtraProveedor(Proveedor proveedorNit, Producto productoCodigoBarras, Integer cantidadExistencias) {
        this.pk = new InfoExtraProveedorPk(proveedorNit, productoCodigoBarras);
        this.cantidadExistencias = cantidadExistencias;
    }

    public InfoExtraProveedorPk getPk() {
        return pk;
    }

    public void setPk(InfoExtraProveedorPk pk) {
        this.pk = pk;
    }

    public Integer getCantidadExistencias() {
        return cantidadExistencias;
    }

    public void setCantidadExistencias(Integer cantidadExistencias) {
        this.cantidadExistencias = cantidadExistencias;
    }

    @Override
    public String toString() {
        return "InfoExtraProveedor{" +
                "pk=" + pk +
                ", cantidadExistencias=" + cantidadExistencias +
                '}';
    }
}
