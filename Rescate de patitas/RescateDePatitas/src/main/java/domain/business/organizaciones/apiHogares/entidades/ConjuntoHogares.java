package domain.business.organizaciones.apiHogares.entidades;

import java.util.List;

public class ConjuntoHogares {
    public int total;
    public String offset;
    public List<Hogar> hogares;

    public int getTotal() { return total; }

    public List <Hogar> getHogares() { return hogares; }
}
