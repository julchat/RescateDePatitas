package domain.business.api.entities;

public class UserData {
    int dni;
    String nombre;
    String apellido;

    // Getters and Setters
    public int getDni() { return dni; }

    public void setDni(int dni) { this.dni = dni; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }

    public void setApellido(String apellido) { this.apellido = apellido; }
}
