package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoCategoria;

    private String caracteristicasAlmacenamiento;
    private String nombreCategoria;
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "CODIGOBARRAS", referencedColumnName = "CODIGOBARRAS")
    private Producto producto;

    public Categoria() {}

    public Categoria(Long codigoCategoria, String caracteristicasAlmacenamiento, String nombreCategoria, String descripcion, Producto producto) {
        this.codigoCategoria = codigoCategoria;
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.producto = producto;
    }

    public Long getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Long codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getCaracteristicasAlmacenamiento() {
        return caracteristicasAlmacenamiento;
    }

    public void setCaracteristicasAlmacenamiento(String caracteristicasAlmacenamiento) {
        this.caracteristicasAlmacenamiento = caracteristicasAlmacenamiento;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "codigoCategoria=" + codigoCategoria +
                ", caracteristicasAlmacenamiento='" + caracteristicasAlmacenamiento + '\'' +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", producto=" + (producto != null ? producto.getCodigoBarras() : "null") +
                '}';
    }
}    