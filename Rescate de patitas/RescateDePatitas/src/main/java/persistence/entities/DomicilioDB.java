package persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "domicilio")
public class DomicilioDB extends EntidadPersistente {

    private String provincia;
    private String localidad;
    private int codigoPostal;
    private String calle;
    private int numero;
    private int departamento;
    private int piso;

    @OneToOne
    @JoinColumn(name = "ubicacion_id")
    private UbicacionDB ubicacion;

// Getters and Setters
    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }

    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public int getCodigoPostal() { return codigoPostal; }

    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getCalle() { return calle; }

    public void setCalle(String calle) { this.calle = calle; }

    public int getNumero() { return numero; }

    public void setNumero(int numero) { this.numero = numero; }

    public int getDepartamento() { return departamento; }

    public void setDepartamento(int departamento) { this.departamento = departamento; }

    public int getPiso() { return piso; }

    public void setPiso(int piso) { this.piso = piso; }

    public UbicacionDB getUbicacion() { return ubicacion; }

    public void setUbicacion(UbicacionDB ubicacion) { this.ubicacion = ubicacion; }
}