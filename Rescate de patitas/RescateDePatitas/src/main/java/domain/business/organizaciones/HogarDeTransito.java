package domain.business.organizaciones;

import domain.business.Domicilio;
import domain.business.MascotaPerdida;
import domain.business.Tamanio;
import domain.business.TipoAnimal;
import domain.business.caracteristicas.Caracteristica;

import java.util.ArrayList;
import java.util.List;

public class HogarDeTransito{
    private Domicilio domicilio;
    private TipoAnimal mascotaQueAcepta;
    private boolean poseePatio;
    private int disponibilidad;
    private List<Caracteristica> caracteristicasAdmitidas = new ArrayList<>();
    private List<MascotaPerdida> mascotasActuales = new ArrayList<>();

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

    public List<MascotaPerdida> getMascotasActuales() {
        return mascotasActuales;
    }

    public void setMascotasActuales(List<MascotaPerdida> mascotasActuales) {
        this.mascotasActuales = mascotasActuales;
    }

    public void agregarMascota(MascotaPerdida mascota) {
        this.mascotasActuales.add(mascota);
    }

    public void entregarMascota(MascotaPerdida mascota) {
        if(this.mascotasActuales.contains(mascota)) {
            this.mascotasActuales.remove(mascota);
        }
        else {
            System.out.println("La mascota buscada no se encuentra en este Hogar de Transito.");
        }
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

    // Metodos
    private boolean cumpleTipoAnimalPermitido(MascotaPerdida mascotaPerdida) {
        if(this.getMascotaQueAcepta().equals(TipoAnimal.CUALQUIERA)) {
            return true;
        }
        else {
            return mascotaPerdida.getTipoAnimal().equals(this.mascotaQueAcepta);
        }
    }

    private boolean cumpleTamanioMascota(MascotaPerdida mascotaPerdida) {
        Tamanio tamanioMascota = mascotaPerdida.getTamanio();
        if(this.isPoseePatio()) {
            return true;
        }
        else {
            return tamanioMascota.equals(Tamanio.PEQUENIA);
        }
    }

    private boolean cumpleConDisponibilidad() {
        return this.getDisponibilidad() >= 1;
    }

    private boolean cumpleCaracteristicas(MascotaPerdida mascotaPerdida) {
        // TODO: habria que filtrar las caracteristicas de la mascota, y compararla con las caracteristicas admitidas del hogar
        return false;       // Por ahora
    }

    public boolean permiteMascotaPerdida(MascotaPerdida mascotaPerdida) {
        if(this.getCaracteristicasAdmitidas().isEmpty()) {
            return (this.cumpleTipoAnimalPermitido(mascotaPerdida) && this.cumpleTamanioMascota(mascotaPerdida) && this.cumpleConDisponibilidad());
        }
        else {
            return (this.cumpleTipoAnimalPermitido(mascotaPerdida) && this.cumpleTamanioMascota(mascotaPerdida) && this.cumpleConDisponibilidad() && this.cumpleCaracteristicas(mascotaPerdida));
        }
    }

    public void cuidarMascota(MascotaPerdida mascotaPerdida) {
        if(this.permiteMascotaPerdida(mascotaPerdida)) {
            mascotaPerdida.ocuparResidencia(this);
            this.disponibilidad--;
            this.agregarMascota(mascotaPerdida);
        }
    }



}
