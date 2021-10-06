package domain.business;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends EntidadPersistente {

    private String usuario;
    private String password;

    @Transient
    private Sesion sesion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor_id")
    private Actor actor;


    //Getters and Setters
    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Sesion getSesion() { return sesion; }

    public void setSesion(Sesion sesion) { this.sesion = sesion; }

    public Actor getActor() { return actor; }

    public void setActor(Actor actor) { this.actor = actor; }

    public User() {}
}
