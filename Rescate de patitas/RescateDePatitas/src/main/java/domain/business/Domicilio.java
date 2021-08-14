package domain.business;

import domain.business.foto.Grande;
import domain.business.organizaciones.Organizacion;

import java.time.LocalDate;

public class Domicilio {
    private String provincia;
    private String localidad;
    private String barrio;
    private String calle;
    private int numero;
    private double latitud;
    private double longitud;

    // Getters and Setters
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }


    // Constructor
    public Domicilio() {}

    public Domicilio(String provincia, String localidad, String barrio, String calle, int numero, int latitud, int longitud) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.barrio = barrio;
        this.calle = calle;
        this.numero = numero;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    //Metodos

    public Organizacion buscarOrganizacionMasCercana(){
        /*List<Organizacion> organizaciones = Organizaciones.getInstance().getOrganizaciones();
        List<HogaresDeTransito> hogares = new ArrayList<>();
        organizaciones.stream().map(unaOrganizacion->getHogaresDeTransito()).values().forEach(unaListaDeHogares -> hogares.add(unaListaDeHogares));
        hogares.stream().map()
        no terminado*/

        //Logica probablemente sujeta a la API de los hogares de transito
        return new Organizacion("prueba", LocalDate.now(),null,null, new Grande(),null);
    }
}
