package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="producto_perecedero")  // Sin espacios
public class ProductoPerecedero extends Producto {

    private String fechaVencimiento;  // Ya no lleva @Id ni @GeneratedValue

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
