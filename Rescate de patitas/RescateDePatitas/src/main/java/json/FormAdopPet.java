package json;

import domain.business.mascota.Mascota;
import domain.business.publicaciones.Pregunta;
import domain.business.users.Persona;

import java.util.List;

public class FormAdopPet {
    Persona persona;
    Mascota mascota;
    List<Pregunta> preguntas;

    public FormAdopPet(Persona persona, Mascota mascota, List<Pregunta> preguntas) {
        this.persona = persona;
        this.mascota = mascota;
        this.preguntas = preguntas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}