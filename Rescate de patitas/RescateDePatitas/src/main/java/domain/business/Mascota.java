package domain.business;

import domain.business.caracteristicas.CaracteristicaConValor;
import domain.business.foto.Foto;
import domain.organizaciones.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class Mascota {
    private String nombreMascota;
    private TipoAnimal tipoAnimal;
    private String apodoMascota;
    private int edadMascota;
    private SexoMascota sexoMascota;                   // M o H, o un Enum con MACHO, HEMBRA? // Enum, para evitar problemas como "no me toma la m porque esta en minuscula"
    private String descripcionMascota;
    private List<Foto> fotos;
    private List<CaracteristicaConValor> caracteristicasMascota;
    private boolean estaPerdida;
    private boolean estaAdoptada;
    private Persona encargado;

    public Mascota(String nombre, TipoAnimal tipo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, List<CaracteristicaConValor> caracs, boolean perdida, boolean adoptada, Persona encargado){
        this.nombreMascota = nombre;
        this.tipoAnimal = tipo;
        this.edadMascota = edadMascota;
        this.sexoMascota = sexo;
        this.descripcionMascota = descripcionMascota;
        this.fotos = fotos;
        this.caracteristicasMascota =caracs;
        this.estaAdoptada = adoptada;
        this.estaPerdida = perdida;
        this.encargado = encargado;
    }

    public Mascota(String nombre, TipoAnimal tipo, int edadMascota, SexoMascota sexo, String descripcionMascota, List<Foto> fotos, List<CaracteristicaConValor> caracs, boolean perdida, boolean adoptada, Duenio encargado) {
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

    public List<Foto> getFoto() {
        return this.fotos;
    }

    public void setFoto(Foto foto) {
        this.fotos = fotos;
    }

    public List<CaracteristicaConValor> getCaracteristicasMascota() {
        return caracteristicasMascota;
    }

    public void setCaracteristicasMascota(List<CaracteristicaConValor> caracteristicasMascota) {
        this.caracteristicasMascota = caracteristicasMascota;
    }

    public void quitarCaracteristica(CaracteristicaConValor caracteristica) {
        if(this.caracteristicasMascota.contains(caracteristica)) {
            this.caracteristicasMascota.remove(caracteristica);
        }
        else {
            System.out.println("La mascota no posee dicha caracteristica.");
        }
    }

    public void agregarCaracteristica(CaracteristicaConValor caracteristica) { this.caracteristicasMascota.add(caracteristica); }

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

    public void ajustarseAOrganizacion(Organizacion organizacion){
        this.fotos.forEach(foto -> foto.normalizarA(organizacion.getDimensionEstandar()));
        List<CaracteristicaConValor> caracteristicasValidas = new ArrayList<>();
        caracteristicasMascota.forEach(unaCaracteristica -> organizacion.agregoSiAceptaCaracteristica(unaCaracteristica, caracteristicasValidas));

        caracteristicasMascota = caracteristicasValidas;
    }

}
