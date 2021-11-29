package domain.business.organizaciones;

import domain.business.EntidadPersistente;
import domain.business.caracteristicas.Caracteristica;
import domain.business.mascota.Mascota;
import domain.business.publicaciones.Pregunta;
import domain.business.users.Persona;
import domain.business.foto.DimensionEstandar;
import domain.business.foto.Foto;
import domain.security.Usuario;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Entity
@Table(name = "organizacion")
public class Organizacion extends EntidadPersistente {

    private String nombreOrganizacion;
    private LocalDate fechaDeCreacion;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "caracteristicasAdmitidas admitidas")
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "logo")
    private Foto logo;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dimensionEstandar")
    private DimensionEstandar dimensionEstandar;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "preguntasOrganizacion")
    private List<Pregunta> preguntasOrganizacion = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "voluntarios")
    private List<Usuario> voluntarios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "administradores")
    private List<Usuario> administradores = new ArrayList<>();

    @Transient
    private List<Usuario> potencialesVoluntarios = new ArrayList<>();

    //@OneToMany(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "mascotasAsociadas")
    // Por ahora Transient
    @Transient
    private List<Mascota> mascotasAsociadas = new ArrayList<>();


    // Getters and Setters
    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public List<Caracteristica> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void agregarCaracteristicaAdmitida(Caracteristica caracteristica) { this.caracteristicasAdmitidas.add(caracteristica); }

    public boolean quitarCaracteristicaAdmitida(Caracteristica nombreCaracteristica) {
        if(this.existeCaracteristica(nombreCaracteristica)){
            this.caracteristicasAdmitidas.remove(this.buscarCaracteristica(nombreCaracteristica));
            return true;
        }
        else {
            return false;
        }
    }

    public Foto getLogo() {
        return logo;
    }

    public void setLogo(Foto logo) {
        this.logo = logo;
    }

    public DimensionEstandar getDimensionEstandar() {
        return dimensionEstandar;
    }

    public void setDimensionEstandar(DimensionEstandar dimensionEstandar) { this.dimensionEstandar = dimensionEstandar; }

    public List<Pregunta> getPreguntasOrganizacion() { return preguntasOrganizacion; }

    public void setPreguntasOrganizacion(List<Pregunta> preguntasOrganizacion) { this.preguntasOrganizacion = preguntasOrganizacion; }

    public List<Usuario> getVoluntarios() { return voluntarios; }

    public void setVoluntarios(List<Usuario> voluntarios) { this.voluntarios = voluntarios; }

    public List<Usuario> getAdministradores() { return administradores; }

    public void setAdministradores(List<Usuario> administradores) { this.administradores = administradores; }

    public List<Usuario> getPotencialesVoluntarios() { return potencialesVoluntarios; }

    public List<Mascota> getMascotasAsociadas() { return mascotasAsociadas; }


    // Constructor
    public Organizacion() {}


    // Metodos
    public void asociarMascota(Mascota nuevaMascota) {
        if(nuevaMascota.getCaracteristicasMascota().stream().allMatch(caracteristica -> this.aceptaCaracteristica(caracteristica))){
            nuevaMascota.getFotos().forEach(foto -> foto.normalizarA(this.getDimensionEstandar()));
            this.getMascotasAsociadas().add(nuevaMascota);
        }
    }

    public boolean aceptaCaracteristica(Caracteristica caracteristicaMascota) {
        if(this.existeCaracteristica(caracteristicaMascota)){
            Caracteristica caracteristica = this.buscarCaracteristica(caracteristicaMascota);
            return caracteristica.equals(caracteristicaMascota);
        }
        else {
            return false;
        }
    }

    private Caracteristica buscarCaracteristica(Caracteristica caracteristicaBuscada) {
        return caracteristicasAdmitidas.stream().filter(caracteristica -> caracteristica.equals(caracteristicaBuscada)).collect(Collectors.toList()).get(0);
    }

    private boolean existeCaracteristica(Caracteristica nombreCaracteristica) {
        return caracteristicasAdmitidas.stream().anyMatch(caracteristica -> caracteristica.equals(nombreCaracteristica));
    }


}
