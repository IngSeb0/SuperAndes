package uniandes.edu.co.parranderos.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "BODEGA")
public class Bodega {

    @EmbeddedId
    private BodegaPK pk;

    private String nombreBodega;
    private Float tamanioBodega;

    public Bodega() {}

    public Bodega(String nombreBodega, Float tamanoBodega, BodegaPK pk) {
        this.nombreBodega = nombreBodega;
        this.tamanioBodega = tamanoBodega;
        this.pk = pk;
    }

    public BodegaPK getPk() {
        return pk;
    }

    public void setPk(BodegaPK pk) {
        this.pk = pk;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public Float getTamanoBodega() {
        return tamanioBodega;
    }

    public void setTamanoBodega(Float tamanoBodega) {
        this.tamanioBodega = tamanoBodega;
    }
}
