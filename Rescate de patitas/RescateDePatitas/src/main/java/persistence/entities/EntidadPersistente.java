package persistence.entities;

import javax.persistence.*;

@MappedSuperclass
public class EntidadPersistente {

    @Id
    //@GeneratedValue
    private int id;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
