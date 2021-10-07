package domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persona")
public class Persona extends EntidadPersistente {

    private LocalDate fechaDeNacimiento;
    private String ciudad;
    private String localidadResidencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foto_id")
    private Foto foto;


    // Getters and Setters
    public LocalDate getFechaDeNacimiento() { return fechaDeNacimiento; }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) { this.fechaDeNacimiento = fechaDeNacimiento; }

    public String getCiudad() { return ciudad; }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public String getLocalidadResidencia() { return localidadResidencia; }

    public void setLocalidadResidencia(String localidadResidencia) { this.localidadResidencia = localidadResidencia; }

    public Foto getFoto() { return foto; }

    public void setFoto(Foto foto) { this.foto = foto; }


    public Persona() {}

    // Metodos
    public void actualizarDatos() {
        // TODO: pedir datos por consola, y actualizar los de esta clase
    }

    public void mostrarDatos() {
        System.out.println("Fecha de Nacimiento: " + this.getFechaDeNacimiento());
        System.out.println("Ciudad: " + this.getCiudad());
        System.out.println("Localidad de Residencia: " + this.getLocalidadResidencia());
    }

}
