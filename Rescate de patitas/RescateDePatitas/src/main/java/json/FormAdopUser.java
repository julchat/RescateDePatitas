package json;

import domain.business.caracteristicas.Caracteristica;
import domain.business.publicaciones.Pregunta;
import domain.business.users.Persona;

import java.util.List;

public class FormAdopUser {
    Persona persona;
    List<Caracteristica> caracteristicas;
    List<Pregunta> preguntas;

    public FormAdopUser(Persona persona, List<Caracteristica> caracteristicas, List<Pregunta> preguntas) {
        this.persona = persona;
        this.caracteristicas = caracteristicas;
        this.preguntas = preguntas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
