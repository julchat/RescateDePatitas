package domain.business.organizaciones;

import domain.business.Domicilio;
import domain.business.TipoAnimal;
import domain.business.caracteristicas.Caracteristica;
import java.util.List;

public class HogarDeTransito{
    private Domicilio domicilio;
    private TipoAnimal mascotaQueAcepta;
    private boolean poseePatio;
    private int disponibilidad;
    private List<Caracteristica> caracteristicasAdmitidas;

    // Getters and Setters
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public TipoAnimal getMascotaQueAcepta() {
        return mascotaQueAcepta;
    }

    public void setMascotaQueAcepta(TipoAnimal mascotaQueAcepta) {
        this.mascotaQueAcepta = mascotaQueAcepta;
    }

    public boolean isPoseePatio() {
        return poseePatio;
    }

    public void setPoseePatio(boolean poseePatio) {
        this.poseePatio = poseePatio;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<Caracteristica> getCaracteristicasAdmitidas() {
        return caracteristicasAdmitidas;
    }

    public void setCaracteristicasAdmitidas(List<Caracteristica> caracteristicasAdmitidas) {
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
    }

    public void agregarCaracteristicaAdmitida(Caracteristica unaCaracteristica) {
        this.caracteristicasAdmitidas.add(unaCaracteristica);
    }

    public void quitarCaracteristicaAdmitida(Caracteristica unaCaracteristica) {
        if(this.caracteristicasAdmitidas.contains(unaCaracteristica)) {
            caracteristicasAdmitidas.remove(unaCaracteristica);
        }
        System.out.println("No posee dicha caracteristicas como caracteristicas admitidas.");
    }

    // Constructor
    public HogarDeTransito(){ }

    public HogarDeTransito(Domicilio domicilio, TipoAnimal mascotaQueAcepta, boolean poseePatio, int disponibilidad, List<Caracteristica> caracteristicasAdmitidas) {
        this.domicilio = domicilio;
        this.mascotaQueAcepta = mascotaQueAcepta;
        this.poseePatio = poseePatio;
        this.disponibilidad = disponibilidad;
        this.caracteristicasAdmitidas = caracteristicasAdmitidas;
    }
}
