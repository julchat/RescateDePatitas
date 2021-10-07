package domain.business.mascota;

import domain.business.users.Persona;
import domain.business.foto.Foto;

import java.util.List;
import java.util.stream.Collectors;

public class Mascota {
    private String nombreMascota;
    private TipoAnimal tipoAnimal;
    private String apodoMascota;
    private int edadMascota;
    private SexoMascota sexoMascota;                   // M o H, o un Enum con MACHO, HEMBRA? // Enum, para evitar problemas como "no me toma la m porque esta en minuscula"
    private String descripcionMascota;
    private List<Foto> fotos;
    private List<String> caracteristicasMascota;

    // Constructor
    public Mascota() {}

    public Mascota(String nombre, TipoAnimal tipo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, List<String> caracteristicasMascota, boolean perdida, boolean adoptada, Persona encargado){
        this.nombreMascota = nombre;
        this.tipoAnimal = tipo;
        this.edadMascota = edadMascota;
        this.sexoMascota = sexo;
        this.descripcionMascota = descripcionMascota;
        this.fotos = fotos;
        this.caracteristicasMascota = caracteristicasMascota;
    }

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

    public SexoMascota getSexoMascota() {
        return sexoMascota;
    }

    public void setSexoMascota(SexoMascota sexoMascota) {
        this.sexoMascota = sexoMascota;
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public List<Foto> getFotos() {
        return this.fotos;
    }

    public void setFotos(List<Foto> foto) {
        this.fotos = fotos;
    }

    public List<String> getCaracteristicasMascota() {
        return caracteristicasMascota;
    }

    public void setCaracteristicasMascota(List<String> caracteristicasMascota) {
        this.caracteristicasMascota = caracteristicasMascota;
    }

    public void quitarCaracteristica(String caracteristicaAQuitar) {
        if(this.caracteristicasMascota.stream().anyMatch(caracteristica -> caracteristica.equals(caracteristicaAQuitar))) {
            this.caracteristicasMascota.remove(this.caracteristicasMascota.stream().filter(caracteristica -> caracteristica.equals(caracteristicaAQuitar)).collect(Collectors.toList()).get(0));
        }
    }

    public void agregarCaracteristica(String caracteristica) { this.caracteristicasMascota.add(caracteristica); }


    // Metodos
    public Chapa generarChapitaIdentificatoria() {
        Chapa chapa = new Chapa();
        return chapa;
    }

    /*
    public void ajustarseAOrganizacion(Organizacion organizacion){
        this.fotos.forEach(foto -> foto.normalizarA(organizacion.getDimensionEstandar()));
        List<CaracteristicaMascota> caracteristicasValidas = new ArrayList<>();
        caracteristicasMascota.forEach(unaCaracteristica -> organizacion.agregoSiAceptaCaracteristica(unaCaracteristica, caracteristicasValidas));

        caracteristicasMascota = caracteristicasValidas;
    }*/

    public void mostrarDatosMascota() {
        System.out.println("Tipo del Animal: " + getTipoAnimal());
        System.out.println("Nombre de la Mascota: " + getNombreMascota());
        System.out.println("Apodo de la Mascota: " + getApodoMascota());
        System.out.println("Edad de la Mascota: " + getEdadMascota());
        System.out.println("Sexo de la Mascota: " + getSexoMascota());
        System.out.println("Descripción de la Mascota: " + getDescripcionMascota());
        System.out.println("Características de la Mascota: " + getCaracteristicasMascota());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println();
    }
}
