package json;

import java.util.List;

public class FormCarac {
    List<String> caracteristicasVisibles;
    List<String> caracteristicasAQuitar;
    String caracteristicaNueva;

    public FormCarac(List<String> caracteristicasVisibles, List<String> caracteristicasAQuitar, String caracteristicaNueva) {
        this.caracteristicasVisibles = caracteristicasVisibles;
        this.caracteristicasAQuitar = caracteristicasAQuitar;
        this.caracteristicaNueva = caracteristicaNueva;
    }

    public List<String> getCaracteristicasVisibles() { return caracteristicasVisibles; }

    public void setCaracteristicasVisibles(List<String> caracteristicasVisibles) { this.caracteristicasVisibles = caracteristicasVisibles; }

    public List<String> getCaracteristicasAQuitar() { return caracteristicasAQuitar; }

    public void setCaracteristicasAQuitar(List<String> caracteristicasAQuitar) { this.caracteristicasAQuitar = caracteristicasAQuitar; }

    public String getCaracteristicaNueva() { return caracteristicaNueva; }

    public void setCaracteristicaNueva(String caracteristicaNueva) { this.caracteristicaNueva = caracteristicaNueva; }
}
