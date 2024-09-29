package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BODEGA")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long BODEGAID;
    @ManyToOne
    @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
    private Sucursal sucursal;

    private String nombreBodegas;
    private Float tamanioBodega;

    public Bodega() {}

    public Bodega(String nombreBodegas, Float tamanioBodega, Sucursal sucursal) {
        this.nombreBodegas = nombreBodegas;
        this.tamanioBodega = tamanioBodega;
        this.sucursal = sucursal;
    }
    public Long getId() {
        return BODEGAID;
    }

    public void setId(Long BODEGAID) {
        this.BODEGAID = BODEGAID;
    }
    
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getNombreBodega() {
        return nombreBodegas;
    }

    public void setNombreBodega(String nombreBodegas) {
        this.nombreBodegas = nombreBodegas;
    }

    public Float getTamanioBodega() {
        return tamanioBodega;
    }

    public void setTamanioBodega(Float tamanioBodega) {
        this.tamanioBodega = tamanioBodega;
    }
}
