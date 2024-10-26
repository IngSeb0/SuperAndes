    package uniandes.edu.co.parranderos.modelo;

    import java.io.Serializable;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;

    @Entity
    @Table(name = "BODEGA")
    public class Bodega implements Serializable {
        @Id
      
        private Long IDBODEGA;
        @ManyToOne
        @JoinColumn(name = "IDSUCURSAL", referencedColumnName = "IDSUCURSAL")
        private Sucursal sucursal;

        private String nombreBodega;
        private Float tamanioBodega;

        public Bodega() {}

        public Bodega(String nombreBodega, Float tamanioBodega, Sucursal sucursal) {
            this.nombreBodega = nombreBodega;
            this.tamanioBodega = tamanioBodega;
            this.sucursal = sucursal;
        }
        public Long getId() {
            return IDBODEGA;
        }

        public void setId(Long IDBODEGA) {
            this.IDBODEGA = IDBODEGA;
        }
        
        public Sucursal getSucursal() {
            return sucursal;
        }

        public void setSucursal(Sucursal sucursal) {
            this.sucursal = sucursal;
        }

        public String getNombreBodega() {
            return nombreBodega;
        }

        public void setNombreBodega(String nombreBodegas) {
            this.nombreBodega = nombreBodegas;
        }

        public Float getTamanioBodega() {
            return tamanioBodega;
        }

        public void setTamanioBodega(Float tamanioBodega) {
            this.tamanioBodega = tamanioBodega;
        }
    }
