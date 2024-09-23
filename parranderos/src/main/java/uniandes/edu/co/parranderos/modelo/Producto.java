package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String codigoBarras;
    private String nombre;
    private Float precioUnitarioVenta;
    private String presentacion;
    private Integer cantidadPresentacion;
    private String unidadMedida;
    private String fechaExpiracion;

    public Producto() {}

    public Producto(String codigoBarras, String nombre, Float precioUnitarioVenta, String presentacion, Integer cantidadPresentacion, String unidadMedida, String fechaExpiracion) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioUnitarioVenta = precioUnitarioVenta;
        this.presentacion = presentacion;
        this.cantidadPresentacion = cantidadPresentacion;
        this.unidadMedida = unidadMedida;
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecioUnitarioVenta() {
        return precioUnitarioVenta;
    }

    public void setPrecioUnitarioVenta(Float precioUnitarioVenta) {
        this.precioUnitarioVenta = precioUnitarioVenta;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Integer getCantidadPresentacion() {
        return cantidadPresentacion;
    }

    public void setCantidadPresentacion(Integer cantidadPresentacion) {
        this.cantidadPresentacion = cantidadPresentacion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(String fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigoBarras='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precioUnitarioVenta=" + precioUnitarioVenta +
                ", presentacion='" + presentacion + '\'' +
                ", cantidadPresentacion=" + cantidadPresentacion +
                ", unidadMedida='" + unidadMedida + '\'' +
                ", fechaExpiracion='" + fechaExpiracion + '\'' +
                '}';
    }
}
