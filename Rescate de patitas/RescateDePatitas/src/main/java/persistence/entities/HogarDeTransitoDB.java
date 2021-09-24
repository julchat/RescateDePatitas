package persistence.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hogar de Tránsito")
public class HogarDeTransitoDB extends EntidadPersistente {

    @Column(name = "Nombre de la Organizacion")
    private String nombreOrganizacion;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Latitud")
    private float latitud;

    @Column(name = "Longitud")
    private float longitud;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "¿Acepta Perros?")
    private boolean aceptaPerros;

    @Column(name = "¿Acepta Gatos?")
    private boolean aceptaGatos;

    @Column(name = "¿Tiene Patio?")
    private boolean poseePatio;

    @Column(name = "Capacidad")
    private int capacidad;

    @Column(name = "Lugares disponibles")
    private int lugaresDisponibles;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Caracteristicas Admitidas")
    private List<CaracteristicaDB> caracteristicasAdmitidas = new ArrayList<CaracteristicaDB>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Mascotas Actuales")
    private List<MascotaPerdidaDB> mascotasActuales = new ArrayList<MascotaPerdidaDB>();


// Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public float getLatitud() { return latitud; }

    public void setLatitud(float latitud) { this.latitud = latitud; }

    public float getLongitud() { return longitud; }

    public void setLongitud(float longitud) { this.longitud = longitud; }

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

    public List<MascotaPerdidaDB> getMascotasActuales() { return mascotasActuales; }

    public void setMascotasActuales(List<MascotaPerdidaDB> mascotasActuales) { this.mascotasActuales = mascotasActuales; }
}
