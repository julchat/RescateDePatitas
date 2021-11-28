package json;

import domain.business.caracteristicas.Caracteristica;
import domain.business.users.Persona;

import java.util.List;

public class FormRegUser {
    Persona persona;
    List<Caracteristica> caracteristicas;

    public FormRegUser(Persona persona, List<Caracteristica> caracteristicas) {
        this.persona = persona;
        this.caracteristicas = caracteristicas;
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
}
