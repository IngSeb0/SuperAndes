package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="PRODUCTOPERECEDERO")  // Nombre de la tabla en la base de datos
public class ProductoPerecedero {
    @Id
    @JoinColumn(name = "CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private String codigoBarras;  

    private String fechaVencimiento;  // Columna FECHAVENCIMIENTO

    public ProductoPerecedero() {}

    public ProductoPerecedero(String codigoBarras, String fechaVencimiento) {
        this.codigoBarras = codigoBarras;
        this.fechaVencimiento = fechaVencimiento;
    }

    // Getters y setters
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
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
                "codigoBarras='" + codigoBarras + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                '}';
    }
}
