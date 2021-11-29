package domain.business.organizaciones;

import domain.business.EntidadPersistente;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.MascotaPerdida;
import domain.business.mascota.Tamanio;
import domain.business.mascota.TipoAnimal;
import domain.business.organizaciones.apiHogares.entidades.Hogar;
import domain.business.ubicacion.Lugar;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hogar_de_transito")
public class HogarDeTransito extends EntidadPersistente {

    private String nombreOrganizacion;

    //private String direccion;
    //private double latitud;
    //private double longitud;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar")
    private Lugar lugar;

    private String telefono;
    private boolean aceptaPerros;
    private boolean aceptaGatos;
    private boolean poseePatio;
    private int capacidad;
    private int lugaresDisponibles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristicasAdmitidas")
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hogarDeTransito")
    private List<MascotaPerdida> mascotasActuales = new ArrayList<>();


    // Getters and Setters
    public String getNombreOrganizacion() { return nombreOrganizacion; }

    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }

    public Lugar getLugar() { return lugar; }

    public void setLugar(Lugar lugar) { this.lugar = lugar; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public boolean aceptaPerros() { return aceptaPerros; }

    public void setAceptaPerros(boolean aceptaPerros) { this.aceptaPerros = aceptaPerros; }

    public boolean aceptaGatos() { return aceptaGatos; }

    public void setAceptaGatos(boolean aceptaGatos) { this.aceptaGatos = aceptaGatos; }

    public boolean poseePatio() {
        return poseePatio;
    }

    public void setPoseePatio(boolean poseePatio) {
        this.poseePatio = poseePatio;
    }

    public int getCapacidad() { return capacidad; }

    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public int getLugaresDisponibles() { return lugaresDisponibles; }

    public void setLugaresDisponibles(int lugaresDisponibles) { this.lugaresDisponibles = lugaresDisponibles; }

    public List<Caracteristica> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void setCaracteristicasAdmitidas(List<Caracteristica> caracteristicasAdmitidas) { this.caracteristicasAdmitidas = caracteristicasAdmitidas; }

    public void agregarCaracteristicaAdmitida(Caracteristica unaCaracteristica) {
        this.caracteristicasAdmitidas.add(unaCaracteristica);
    }

    public void quitarCaracteristicaAdmitida(String unaCaracteristica) {
        if(this.caracteristicasAdmitidas.contains(unaCaracteristica)) {
            caracteristicasAdmitidas.remove(unaCaracteristica);
        }
        System.out.println("No posee dicha caracteristicas como caracteristicas admitidas.");
    }

    public List<MascotaPerdida> getMascotasActuales() {
        return mascotasActuales;
    }

    public void setMascotasActuales(List<MascotaPerdida> mascotasActuales) {
        this.mascotasActuales = mascotasActuales;
    }

    public void agregarMascota(MascotaPerdida mascota) {
        this.mascotasActuales.add(mascota);
    }

    public void entregarMascota(MascotaPerdida mascota) {
        if(this.mascotasActuales.contains(mascota)) {
            this.mascotasActuales.remove(mascota);
        }
        else {
            System.out.println("La mascota buscada no se encuentra en este Hogar de Transito.");
        }
    }


    // Constructor
    public HogarDeTransito(){ }


    // Metodos
    public void mappearHogar(Hogar hogar) {
        this.setNombreOrganizacion(hogar.getNombre());
        this.setLugar(new Lugar(hogar.getUbicacion().getDireccion(), hogar.getUbicacion().getLat(), hogar.getUbicacion().getLongitud()));
        this.setTelefono(hogar.getTelefono());
        this.setAceptaPerros(hogar.getAdmisiones().admitePerros());
        this.setAceptaGatos(hogar.getAdmisiones().admiteGatos());
        this.setCapacidad(hogar.getCapacidad());
        this.setLugaresDisponibles(hogar.getLugares_disponibles());
        this.setPoseePatio(hogar.isPatio());
        this.setCaracteristicasAdmitidas(hogar.getCaracteristicas());
    }

    private boolean cumpleTipoAnimalPermitido(MascotaPerdida mascotaPerdida) {
        if(mascotaPerdida.getTipoAnimal().equals(TipoAnimal.PERRO) && this.aceptaPerros) {
            return true;
        }
        else if(mascotaPerdida.getTipoAnimal().equals(TipoAnimal.GATO) && this.aceptaGatos) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean cumpleTamanioMascota(MascotaPerdida mascotaPerdida) {
        Tamanio tamanioMascota = mascotaPerdida.getTamanio();
        if(this.poseePatio()) {
            return true;
        }
        else {
            return tamanioMascota.equals(Tamanio.PEQUENIA);
        }
    }

    private boolean tieneDisponibilidad() {
        return this.getLugaresDisponibles() >= 1;
    }

    private boolean cumpleCaracteristicas(MascotaPerdida mascotaPerdida) {
        for(Caracteristica caracteristica : caracteristicasAdmitidas) {
            if(!mascotaPerdida.cumpleCaracteristicaHogar(caracteristica)){
                return false;
            }
        }
        return true;
    }

    public boolean permiteMascotaPerdida(MascotaPerdida mascotaPerdida) {
        if(this.getCaracteristicasAdmitidas().isEmpty()) {
            return (this.cumpleTipoAnimalPermitido(mascotaPerdida) && this.cumpleTamanioMascota(mascotaPerdida) && this.tieneDisponibilidad());
        }
        else {
            return (this.cumpleTipoAnimalPermitido(mascotaPerdida) && this.cumpleTamanioMascota(mascotaPerdida) && this.tieneDisponibilidad() && this.cumpleCaracteristicas(mascotaPerdida));
        }
    }

    public void alojarMascota(MascotaPerdida mascotaPerdida) {
        if(this.permiteMascotaPerdida(mascotaPerdida)) {
            mascotaPerdida.ocuparLugarDeTransito(this.getLugar());
            this.lugaresDisponibles--;
            this.agregarMascota(mascotaPerdida);
        }
    }

    public double distancia(double lat1, double lng1, double lat2, double lng2) {
        double PI = 3.14159265358979323; // pi
        double R = 6371229; // radio de la tierra

        double x, y, distance;
        x = (lng2 - lng1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
        y = (lat2 - lat1) * PI * R / 180;
        distance = Math.hypot(x, y);
        return distance;
    }

    public boolean masCercanoQue(HogarDeTransito otroHogar, Lugar lugarEncontrada) {
        return this.distancia(this.getLugar().getLatitud(), this.getLugar().getLongitud(), lugarEncontrada.getLatitud(), lugarEncontrada.getLongitud()) >= otroHogar.distancia(otroHogar.getLugar().getLatitud(), otroHogar.getLugar().getLongitud(), lugarEncontrada.getLatitud(), lugarEncontrada.getLongitud());
    }
}
