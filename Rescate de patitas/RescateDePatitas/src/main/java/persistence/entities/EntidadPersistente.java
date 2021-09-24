package persistence.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    public int getId() { return id; }
}
