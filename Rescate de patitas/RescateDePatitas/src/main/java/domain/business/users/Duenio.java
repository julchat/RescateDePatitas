package domain.business.users;
/*
import domain.business.mascota.Chapa;
import domain.business.mascota.Mascota;
import domain.business.ubicacion.Domicilio;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "duenio")
@DiscriminatorValue("Duenio")
public class Duenio extends Persona {

    @OneToMany(cascade = CascadeType.PERSIST)
    @Column(name = "mascotasACargo")
    private List<Chapa> mascotasACargo = new ArrayList<>();


    // Getters and Setters
    public List<Chapa> getMascotasACargo() { return mascotasACargo; }

    public void setMascotasACargo(List<Chapa> mascotasACargo) { this.mascotasACargo = mascotasACargo; }

    public Duenio() {}


    // Metodos
    public void registrarMascota(Chapa nuevaChapa) throws IOException {
        this.getMascotasACargo().add(nuevaChapa);
    }



    @Override
    public void mostrarDatosNoSensibles() {
        super.mostrarDatosNoSensibles();
        System.out.println("Domicilio: ");
        System.out.println("    - Provincia: " + getDomicilio().getProvincia());
        System.out.println("    - Localidad: " + getDomicilio().getLocalidad());
        System.out.println("    - Calle: " + getDomicilio().getCalle());
        System.out.println("    - Numeración: " + getDomicilio().getNumeracion());
        this.mostrarMascotas();
    }

}
*/