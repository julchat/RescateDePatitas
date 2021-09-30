package persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pregunta")
public class PreguntaDB extends EntidadPersistente {

    private String pregunta;


// Getters and Setters
    public String getPregunta() { return pregunta; }

    public void setPregunta(String pregunta) { this.pregunta = pregunta; }
}
