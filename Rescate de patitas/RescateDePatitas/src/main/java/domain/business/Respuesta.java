package domain.business;

public class Respuesta {
    private Pregunta pregunta;
    private String respuesta;

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

    public Respuesta(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public void responderPregunta(String respuesta) {
        this.respuesta = respuesta;
    }
}
