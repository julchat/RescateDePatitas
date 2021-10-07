package database.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hogar_de_transito")
public class HogarDeTransitoDB extends EntidadPersistente {

    private String nombreOrganizacion;
    private String direccion;
    private double latitud;
    private double longitud;
    private String telefono;
    private boolean aceptaPerros;
    private boolean aceptaGatos;
    private boolean poseePatio;
    private int capacidad;
    private int lugaresDisponibles;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "caracteristicas_admitidas")
    private List<CaracteristicaDB> caracteristicasAdmitidas = new ArrayList<>();

    // TODO: Como una mascota tiene LugarDB, entonces puede meterse el Lugar de este HogarDeTransito, y no hace falta tener la lista aca
    //@OneToMany(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "mascotas_actuales")
    //private List<MascotaPerdidaDB> mascotasActuales = new ArrayList<>();


// Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getLatitud() { return latitud; }

    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }

    public void setLongitud(double longitud) { this.longitud = longitud; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public boolean isAceptaPerros() { return aceptaPerros; }

    public void setAceptaPerros(boolean aceptaPerros) { this.aceptaPerros = aceptaPerros; }

    public boolean isAceptaGatos() { return aceptaGatos; }

    public void setAceptaGatos(boolean aceptaGatos) { this.aceptaGatos = aceptaGatos; }

    public boolean isPoseePatio() { return poseePatio; }

    public void setPoseePatio(boolean poseePatio) { this.poseePatio = poseePatio; }

    public int getCapacidad() { return capacidad; }

    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public int getLugaresDisponibles() { return lugaresDisponibles; }

    public void setLugaresDisponibles(int lugaresDisponibles) { this.lugaresDisponibles = lugaresDisponibles; }

    public List<CaracteristicaDB> getCaracteristicasAdmitidas() { return caracteristicasAdmitidas; }

    public void setCaracteristicasAdmitidas(List<CaracteristicaDB> caracteristicasAdmitidas) { this.caracteristicasAdmitidas = caracteristicasAdmitidas; }
}