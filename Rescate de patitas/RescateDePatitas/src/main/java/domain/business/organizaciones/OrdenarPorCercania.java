package domain.business.organizaciones;

import domain.business.ubicacion.Lugar;

import java.util.Comparator;

public class OrdenarPorCercania implements Comparator<HogarDeTransito> {
    Lugar ubicacionMascota;
    @Override
    public int compare(HogarDeTransito o1, HogarDeTransito o2) {
       return new Double(o1.distancia(o1.getLatitud(), o1.getLongitud(), ubicacionMascota.getLatitud(), ubicacionMascota.getLongitud())).intValue() -
                new Double(o2.distancia(o2.getLatitud(), o2.getLongitud(), ubicacionMascota.getLatitud(), ubicacionMascota.getLongitud())).intValue();
    }

    public OrdenarPorCercania(Lugar ubicacionMascota){
        this.ubicacionMascota = ubicacionMascota;
    }
}
