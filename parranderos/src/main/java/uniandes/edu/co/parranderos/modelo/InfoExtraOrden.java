package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "INFOEXTRAORDEN")
public class InfoExtraOrden {

    @EmbeddedId
    private InfoExtraOrdenPk pk;

    private Long cantidad;
    private Float costoUnitario;

    public InfoExtraOrden() {; }

    public InfoExtraOrden(OrdenCompra ordenCompra, Producto producto, Long cantidad, Float costoUnitario) {
        this.pk = new InfoExtraOrdenPk(ordenCompra, producto);
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
    }

    public InfoExtraOrdenPk getPk() {
        return pk;
    }

    public void setPk(InfoExtraOrdenPk pk) {
        this.pk = pk;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    @Override
    public String toString() {
        return "InfoExtraOrden{" +
                "pk=" + pk +
                ", cantidad=" + cantidad +
                ", costoUnitario=" + costoUnitario +
                '}';
    }
}
