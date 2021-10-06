package domain.business;

import domain.business.api.entities.UserData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Actor extends EntidadPersistente {

    private String nombre;
    private String apellido;
    private int nroDocumento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "autorizaciones_emitidas")
    private List<Autorizacion> autorizacionesEmitidas;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "autorizaciones_recibidas")
    private List<Autorizacion> autorizacionesRecibidas;


    // Getters and Setters
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getNroDocumento() { return nroDocumento; }

    public void setNroDocumento(int nroDocumento) { this.nroDocumento = nroDocumento; }

    public Persona getPersona() { return persona; }

    public void setPersona(Persona persona) { this.persona = persona; }

    public List<Autorizacion> getAutorizacionesEmitidas() { return autorizacionesEmitidas; }

    public void setAutorizacionesEmitidas(List<Autorizacion> autorizacionesEmitidas) { this.autorizacionesEmitidas = autorizacionesEmitidas; }

    public List<Autorizacion> getAutorizacionesRecibidas() { return autorizacionesRecibidas; }

    public void setAutorizacionesRecibidas(List<Autorizacion> autorizacionesRecibidas) { this.autorizacionesRecibidas = autorizacionesRecibidas; }


    public Actor() {
        this.autorizacionesEmitidas = new ArrayList<Autorizacion>();
        this.autorizacionesRecibidas = new ArrayList<Autorizacion>();
    }


    // Metodos
    public void actualizarDatos() {
        persona.actualizarDatos();
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Apellido: " + this.getApellido());
        System.out.println("Nro. Documento: " + this.getNroDocumento());
        persona.mostrarDatos();
    }

    public void autorizarPersona(Persona autorizado) {

    }

    public void mapearUsuario(UserData userData) {
        this.setNombre(userData.getNombre());
        this.setApellido(userData.getApellido());
        this.setNroDocumento(userData.getDni());
    }
}
