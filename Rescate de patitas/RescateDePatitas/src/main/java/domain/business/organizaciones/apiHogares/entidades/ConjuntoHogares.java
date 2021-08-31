package domain.business.organizaciones.apiHogares.entidades;

import java.util.List;

public class ConjuntoHogares {
    public int cantidad;
    public String offset;
    public List<Hogar> hogares;

    public int getTotal() { return cantidad; }

    public List <Hogar> getHogares() { return hogares; }
}
