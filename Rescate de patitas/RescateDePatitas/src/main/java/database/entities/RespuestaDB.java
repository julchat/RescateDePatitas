package database.entities;

import javax.persistence.*;

@Entity
@Table(name = "respuesta")
public class RespuestaDB extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pregunta")
    private PreguntaDB pregunta;

    private String respuesta;


// Getters and Setters
    public String getRespuesta() { return respuesta; }

    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
}
