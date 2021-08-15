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
    private Ubicacion ubicacion;

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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    // Constructor
    public Domicilio() {}

    public Domicilio(String provincia, String localidad, String barrio, String calle, int numero, Ubicacion ubicacion) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.barrio = barrio;
        this.calle = calle;
        this.numero = numero;
        this.ubicacion = ubicacion;
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
