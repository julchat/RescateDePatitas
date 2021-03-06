package domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntidadPersistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
