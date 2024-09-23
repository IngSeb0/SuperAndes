package uniandes.edu.co.parranderos.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Productos Perecederos")
public class ProductoPerecedero extends Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String fechaVencimiento;

    public ProductoPerecedero() {}

    public ProductoPerecedero(String codigoBarras, String nombre, Float precioUnitarioVenta, String presentacion, Integer cantidadPresentacion, String unidadMedida, String fechaExpiracion, String fechaVencimiento) {
        super(codigoBarras, nombre, precioUnitarioVenta, presentacion, cantidadPresentacion, unidadMedida, fechaExpiracion);
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public String toString() {
        return "ProductoPerecedero{" +
                "fechaVencimiento='" + fechaVencimiento + '\'' +
                "} " + super.toString();
    }
}
