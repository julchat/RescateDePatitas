package persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Rol")
public class RolDB extends EntidadPersistente {

    @OneToMany
    @Column(name = "Tipo Rol")
    private TipoRol tipoRol;


// Getters and Setters
    public TipoRol getTipoRol() { return tipoRol; }

    public void setTipoRol(TipoRol tipoRol) { this.tipoRol = tipoRol; }
}
