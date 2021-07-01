package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseCompuesta extends Base {
    private ArrayList<Base> bases;

    // Getters and Setters
    public ArrayList<Base> getBases() {
        return bases;
    }

    public void setBases(ArrayList<Base> bases) {
        this.bases = bases;
    }

    public void agregarBase(Base unaBase) {
        this.bases.add(unaBase);
    }

    public void quitarBase(Base unaBase) {
        if(this.bases.contains(unaBase)) {
            this.bases.remove(unaBase);
        }
        else {
            System.out.println("No existe la base " + unaBase.getNombreBase() + " dentro de las bases de esta Base Compuesta.");
        }
    }

    // Constructores
    public BaseCompuesta() { }

    public BaseCompuesta(String nombreBase, ArrayList<Base> bases) {
        super(nombreBase);
        this.bases = bases;
    }

    public int cantidadDeBases() {
        return this.bases.size();
    }

    @Override
    public int cantidadDeAmbulancias() {
        return this.bases.stream().mapToInt(unaBase -> unaBase.cantidadDeAmbulancias()).sum();
    }

    @Override
    public float tiempoMedio() {
        float tiempoMedioTotal = (float)this.bases.stream().mapToDouble(unaBase -> unaBase.tiempoMedio()).sum();
        return (tiempoMedioTotal / this.cantidadDeBases());
    }
}
