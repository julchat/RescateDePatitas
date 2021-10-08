package domain.business.publicaciones;


import domain.business.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "respuesta")
public class Respuesta extends EntidadPersistente {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pregunta")
    private Pregunta pregunta;

    private String respuesta;


    // Getters and Setters
    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Respuesta() { }

    public Respuesta(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }


    // Metodos
    public void responderPregunta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void mostrarRespuesta() {
        System.out.println("Pregunta: " + getPregunta());
        System.out.println(" - " + getRespuesta());
    }
}
