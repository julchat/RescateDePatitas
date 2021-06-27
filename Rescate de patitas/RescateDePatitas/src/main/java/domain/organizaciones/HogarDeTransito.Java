package domain.business.organizaciones
import domain.business.Domicilio

public class HogarDeTransito{
    private Domicilio domicilio;

    public HogarDeTransito(){

    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

}
