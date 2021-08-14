package domain.business.publicaciones;

import domain.business.Duenio;
import domain.business.Mascota;
import domain.business.Persona;
import domain.business.Respuesta;

import java.util.List;

public class PuestoEnAdopcion extends TipoPublicacion{
    private List<Respuesta> respuestasOrganizacion;
    private Duenio duenioActual;
    private Mascota mascotaElegida;
    private List<Persona> personasInteresadas;


    public void nuevoInteresado(Persona interesado) {
        this.personasInteresadas.add(interesado);
    }
}
