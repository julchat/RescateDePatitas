package domain.business.publicaciones;

import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pregunta")
public class Pregunta extends EntidadPersistente {

    private String pregunta;


    // Getters and Setters
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Pregunta() { }
}
