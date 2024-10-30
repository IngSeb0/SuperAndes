package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "INFOEXTRAVENTA")
public class InfoExtraVenta {

    @EmbeddedId
    private InfoExtraVentaPk pk;

    private Integer cantidad;
    private Float precioUnitario;

    public InfoExtraVenta() {;}

    public InfoExtraVenta(Venta idVenta, Producto idProducto, Integer cantidad, Float precioUnitario) {
        this.pk = new InfoExtraVentaPk(idVenta, idProducto);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public InfoExtraVentaPk getPk() {
        return pk;
    }

    public void setPk(InfoExtraVentaPk pk) {
        this.pk = pk;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "InfoExtraVenta{" +
                "pk=" + pk +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
