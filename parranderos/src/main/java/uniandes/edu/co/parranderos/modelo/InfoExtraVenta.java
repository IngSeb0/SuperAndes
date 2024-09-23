package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Info extra ventas")


public class InfoExtraVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cantidad;
    private Float precioUnitarioVenta;

    public InfoExtraVenta() {}

    public InfoExtraVenta(Integer cantidad, Float precioUnitarioVenta) {
        this.cantidad = cantidad;
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitarioVenta() {
        return precioUnitarioVenta;
    }

    public void setPrecioUnitarioVenta(Float precioUnitarioVenta) {
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    @Override
    public String toString() {
        return "InfoExtraVenta{" +
                "cantidad=" + cantidad +
                ", precioUnitarioVenta=" + precioUnitarioVenta +
                '}';
    }
}
