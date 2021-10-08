package domain.business;

import javax.persistence.*;

@MappedSuperclass
public class EntidadPersistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
