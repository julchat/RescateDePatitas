package domain.business.users;

import domain.business.EntidadPersistente;
import domain.business.Sistema;
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.mascota.MascotaPerdida;
import domain.business.notificaciones.Notificacion;
import domain.business.organizaciones.Organizacion;
import domain.business.publicaciones.*;
import domain.business.ubicacion.Domicilio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "persona")
public class Persona extends EntidadPersistente {

    private String nombre;
    private String apellido;
    private LocalDate fechaDeNacimiento;

    @Enumerated(EnumType.STRING)
    private TipoDoc tipoDocumento;

    private int numeroDocumento;
    private String telefono;
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "domicilio")
    private Domicilio domicilio;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "formasDeNotificacion")
    private List<Notificacion> formasDeNotificacion = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "contactos")
    private List<Contacto> contactos;

    private boolean puedeAlojarMascota;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "persona_id")
    private List<MascotaPerdida> mascotasAlojadas;

    private boolean suscripto;


    // Getters and Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public TipoDoc getTipoDocumento() { return tipoDocumento; }

    public void setTipoDocumento(TipoDoc tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public int getNumeroDocumento() { return numeroDocumento; }

    public void setNumeroDocumento(int numeroDocumento) { this.numeroDocumento = numeroDocumento; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Domicilio getDomicilio() { return domicilio; }

    public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio; }

    public List<Notificacion> getFormasDeNotificacion() { return formasDeNotificacion; }

    public void setFormasDeNotificacion(List<Notificacion> formasDeNotificacion) { this.formasDeNotificacion = formasDeNotificacion; }

    public List<Contacto> getContactos() { return contactos; }

    public void setContactos(List<Contacto> contactos) { this.contactos = contactos; }

    public boolean isPuedeAlojarMascota() { return puedeAlojarMascota; }

    public void setPuedeAlojarMascota(boolean puedeAlojarMascota) { this.puedeAlojarMascota = puedeAlojarMascota; }

    public List<MascotaPerdida> getMascotasAlojadas() { return mascotasAlojadas; }

    public void setMascotasAlojadas(List<MascotaPerdida> mascotasAlojadas) {
        this.mascotasAlojadas = mascotasAlojadas;
    }

    public void suscribirseNovedades() { this.suscripto = true; }

    public void desuscribirseNovedades() {
        this.suscripto = false;
    }


    // Constructor
    public Persona() {}

    // Metodos
    public void buscarPublicacionesMascotaEnAdopcion(){
        Sistema miSistema = Sistema.getInstance();
        miSistema.getPublicaciones().stream().filter(publicacion -> publicacion.getClass().equals(PublicacionMascotaEnAdopcion.class)).collect(Collectors.toList());

        PublicacionMascotaEnAdopcion publicacionElegida = new PublicacionMascotaEnAdopcion();
        this.mostrarInteresEnPublicacion(publicacionElegida);
    }

    private void mostrarInteresEnPublicacion(PublicacionMascotaEnAdopcion publicacionElegida) {
        publicacionElegida.nuevoInteresado(this);
        //publicacionElegida.getAutor().getFormasDeNotificacion().forEach(notificacion -> notificacion.notificarHayInteresadoEnAdoptar(publicacionElegida.getAutor(), this, publicacionElegida.getMascotaElegida(), publicacionElegida.getRuta()));
    }

    public List<Publicacion> buscarPublicacionesMascotaPerdida(){
        Sistema miSistema = Sistema.getInstance();
        return miSistema.getPublicaciones().stream().filter(publicacion -> publicacion.getClass().equals(PublicacionMascotaPerdida.class)).collect(Collectors.toList());
    }
}
