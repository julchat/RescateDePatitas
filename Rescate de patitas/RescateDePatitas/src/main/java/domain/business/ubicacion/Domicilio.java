package domain.business.ubicacion;

import domain.business.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "domicilio")
public class Domicilio extends EntidadPersistente {

    private String provincia;
    private String localidad;
    private int codigoPostal;
    private String calle;
    private int numeracion;
    private int departamento;
    private int piso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lugar")
    private Lugar lugar;

    // Getters and Setters
    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }

    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public int getCodigoPostal() { return codigoPostal; }

    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getCalle() { return calle; }

    public void setCalle(String calle) { this.calle = calle; }

    public int getNumeracion() { return numeracion; }

    public void setNumeracion(int numeracion) { this.numeracion = numeracion; }

    public int getDepartamento() { return departamento; }

    public void setDepartamento(int departamento) { this.departamento = departamento; }

    public int getPiso() { return piso; }

    public void setPiso(int piso) { this.piso = piso; }

    public Lugar getLugar() { return lugar; }

    public void setLugar(Lugar lugar) { this.lugar = lugar; }


    // Constructor
    public Domicilio() {
        this.provincia = null;
        this.localidad = null;
        this.calle = null;
    }

    public Domicilio(String provincia, String localidad, int codigoPostal, String calle, int numeracion, int departamento, int piso) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numeracion = numeracion;
        this.departamento = departamento;
        this.piso = piso;
    }

    //Metodos
   /* public Organizacion buscarOrganizacionMasCercana(){
        List<Organizacion> organizaciones = Sistema.getInstance().getOrganizaciones();
        List<HogaresDeTransito> hogares = new ArrayList<>();
        organizaciones.stream().map(unaOrganizacion->getHogaresDeTransito()).values().forEach(unaListaDeHogares -> hogares.add(unaListaDeHogares));
        hogares.stream().map()
        //no terminado

        //Logica probablemente sujeta a la API de los hogares de transito
        return new Organizacion("prueba", LocalDate.now(),null,null, new Grande(),null);
    }*/

}
