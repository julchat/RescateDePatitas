package json;

public class Pet {
    public String nombreMascota;
    public String apodoMascota;
    public String edadMascota;
    public String descripcionMascota;
    public String sexoMascota;
    public String tipoAnimal;


    public Pet(String nombreMascota, String apodoMascota, String edadMascota, String descripcionMascota, String sexoMascota, String tipoAnimal) {
        this.nombreMascota = nombreMascota;
        this.apodoMascota = apodoMascota;
        this.edadMascota = edadMascota;
        this.descripcionMascota = descripcionMascota;
        this.sexoMascota = sexoMascota;
        this.tipoAnimal = tipoAnimal;
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

    public String getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(String sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}




