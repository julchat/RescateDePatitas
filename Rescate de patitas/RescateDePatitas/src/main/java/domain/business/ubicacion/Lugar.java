package domain.business.ubicacion;


import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lugar")
public class Lugar extends EntidadPersistente {

    private String direccion;

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;


    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Ubicacion getUbicacion() { return ubicacion; }

    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }

    // Constructor
    public Lugar() {}


    // Metodos
    public Lugar mapearLugar(Domicilio domicilio) {
        String direccion = domicilio.getCalle() + " " + domicilio.getNumero() + ", " + domicilio.getLocalidad() + ", " + domicilio.getProvincia();
        this.setDireccion(direccion);
        this.setUbicacion(new Ubicacion());
        this.getUbicacion().setLatitud(domicilio.getUbicacion().getLatitud());
        this.getUbicacion().setLongitud(domicilio.getUbicacion().getLongitud());
        return this;
    }
}
