package domain;

import java.util.List;

public class Control {
    private List<Base> bases;

    public Control() {}

    public void agregarBase(Base unaBase) {
        this.bases.add(unaBase);
    }

    public void quitarBase(Base unaBase) {
        this.bases.remove(unaBase);
    }



}
