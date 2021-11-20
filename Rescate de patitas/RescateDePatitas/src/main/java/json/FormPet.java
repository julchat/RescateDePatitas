package json;

public class FormPet {


    public String tipoPerro;
    public String tipoGato;
    public String sexoHembra;
    public String sexoMacho;
    public String nombreMascota;
    public String apodoMascota;
    public String edadMascota;
    public String descripcionMascota;

    public FormPet(String tipoPerro, String tipoGato, String sexoHembra, String sexoMacho, String nombreMascota, String apodoMascota, String edadMascota, String descripcionMascota) {
        this.tipoPerro = tipoPerro;
        this.tipoGato = tipoGato;
        this.sexoHembra = sexoHembra;
        this.sexoMacho = sexoMacho;
        this.nombreMascota = nombreMascota;
        this.apodoMascota = apodoMascota;
        this.edadMascota = edadMascota;
        this.descripcionMascota = descripcionMascota;
    }

    public String getTipoPerro() {
        return tipoPerro;
    }

    public void setTipoPerro(String tipoPerro) {
        this.tipoPerro = tipoPerro;
    }

    public String getTipoGato() {
        return tipoGato;
    }

    public void setTipoGato(String tipoGato) {
        this.tipoGato = tipoGato;
    }

    public String getSexoHembra() {
        return sexoHembra;
    }

    public void setSexoHembra(String sexoHembra) {
        this.sexoHembra = sexoHembra;
    }

    public String getSexoMacho() {
        return sexoMacho;
    }

    public void setSexoMacho(String sexoMacho) {
        this.sexoMacho = sexoMacho;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getApodoMascota() {
        return apodoMascota;
    }

    public void setApodoMascota(String apodoMascota) {
        this.apodoMascota = apodoMascota;
    }

    public String getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(String edadMascota) {
        this.edadMascota = edadMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }
}
