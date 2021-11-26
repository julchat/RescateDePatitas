package domain.business.users;

import domain.business.EntidadPersistente;
import domain.business.Sistema;
import domain.business.mascota.Mascota;
import domain.business.notificaciones.Notificacion;
import domain.business.publicaciones.*;
import domain.business.ubicacion.Domicilio;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "formasDeNotificacion")
    private List<Notificacion> formasDeNotificacion = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactos")
    private List<Contacto> contactos;

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

    public void agregarNuevaFormaDeNotificacion(Notificacion nuevaFormaDeNotificacion) { this.formasDeNotificacion.add(nuevaFormaDeNotificacion); }

   public void quitarFormaDeNotificacion(Notificacion formaDeNotificacion) {
        if(this.formasDeNotificacion.contains(formaDeNotificacion)) {
            this.formasDeNotificacion.remove(formaDeNotificacion);
        }
        else {
            System.out.println("Dicha forma de Notificación no se encuentra en la lista de notificaciones de la Persona.");
        }
   }

    public List<Contacto> getContactos() { return contactos; }

    public void setContactos(List<Contacto> contactos) { this.contactos = contactos; }

    public void agregarContacto(Contacto nuevoContacto) { this.contactos.add(nuevoContacto);}

    public void quitarContacto(Contacto contacto) {
        if(this.contactos.contains(contacto)) {
            this.contactos.remove(contacto);
        }
        else {
            System.out.println("El contacto no se encuentra en la lista de contactos de la Persona");
        }
    }

    public void suscribirseNovedades() {
        this.suscripto = true;
    }

    public void desuscribirseNovedades() {
        this.suscripto = false;
    }


    // Constructor
    public Persona() {}

    // Metodos
    public void crearPublicacionParaAdoptar() {
        List<String> comodidades = new ArrayList<>();
        List<String> preferencias = new ArrayList<>();

        //PublicacionParaAdoptar publicacionParaAdoptar = new PublicacionParaAdoptar();
        //publicacionParaAdoptar.crearPublicacion(this, comodidades, preferencias);
        // TODO: agregar la publicacion al Repositorio de Publicaciones
    }

    public void crearPublicacionParaDarAdopcion() {
        PublicacionMascotaEnAdopcion publicacionCreada = new PublicacionMascotaEnAdopcion();
        Mascota mascotaElegida = new Mascota();

        List<Respuesta> respuestas = new ArrayList<>();

        publicacionCreada.crearPublicacion(this, mascotaElegida, respuestas);
        // TODO: agregar la publicacion al Repositorio de Publicaciones
    }

    // Todo: no es la parte final
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

    public void comunicarseConAutor(Publicacion publicacion) {
        publicacion.getAutor().mostrarDatosNoSensibles();
    }


    public void mostrarDatosNoSensibles() {
        System.out.println("Nombre y Apellido: " + getNombre() + " " + getApellido());
        System.out.println("Email: " + getEmail());
        System.out.println("Teléfono: " + getTelefono());
        if(!getContactos().isEmpty()) {
            System.out.println("Contacto/s: ");
            for(Contacto contacto : getContactos()){
                System.out.println(" - Nombre y Apellido: " + contacto.getNombreContacto() + " " + contacto.getApellidoContacto());
                System.out.println(" - Email Contacto: " + contacto.getEmailContacto());
                System.out.println(" - Teléfono Contacto: " + contacto.getTelefonoContacto());
            }
        }
    }
}
