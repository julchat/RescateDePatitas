package domain;

import domain.caracteristicas.CaracteristicaConValor;
import domain.foto.Foto;

import java.util.List;

public class Mascota {
    private String nombreMascota;
    private TipoAnimal tipoAnimal;
    private String apodoMascota;
    private int edadMascota;
    private char sexoMascota;                   // M o H, o un Enum con MACHO, HEMBRA?
    private String descripcionMascota;
    private Foto foto;
    private List<CaracteristicaConValor> caracteristicasMascota;
    private boolean estaPerdida;
    private boolean estaAdoptada;
    private Persona encargado;


    // Getters and Setters
    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public String getApodoMascota() {
        return apodoMascota;
    }

    public void setApodoMascota(String apodoMascota) {
        this.apodoMascota = apodoMascota;
    }

    public int getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    public char getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(char sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<CaracteristicaConValor> getCaracteristicasMascota() {
        return caracteristicasMascota;
    }

    public void setCaracteristicasMascota(List<CaracteristicaConValor> caracteristicasMascota) {
        this.caracteristicasMascota = caracteristicasMascota;
    }

    public void quitarCaracteristica(CaracteristicaConValor caracteristica) {
        this.caracteristicasMascota.remove(caracteristica);
    }

    public void agregarCaracteristica(CaracteristicaConValor caracteristica) {
        this.caracteristicasMascota.add(caracteristica);
    }

    void serEncontrada() {
        this.estaPerdida = false;
    }

    void serAdoptada() {
        this.estaAdoptada = true;
    }

    public Persona getEncargado() {
        return encargado;
    }

    public void setEncargado(Persona encargado) {
        this.encargado = encargado;
    }


    // Metodos
    Chapa generarChapitaIdentificatoria() {
        Chapa chapa = new Chapa();
        return chapa;
    }

}
