package domain.business.ubicacion;

import domain.business.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "domicilio")
public class Domicilio extends EntidadPersistente {

    private String provincia;
    private String localidad;
    private int codigoPostal;
    private String calle;
    private int numero;
    private int departamento;
    private int piso;

    @OneToOne
    @JoinColumn(name = "ubicacion")
    private Ubicacion ubicacion;


    // Getters and Setters
    public String getProvincia() { return provincia; }

    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }

    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public int getCodigoPostal() { return codigoPostal; }

    public void setCodigoPostal(int codigoPostal) { this.codigoPostal = codigoPostal; }

    public String getCalle() { return calle; }

    public void setCalle(String calle) { this.calle = calle; }

    public int getNumero() { return numero; }

    public void setNumero(int numero) { this.numero = numero; }

    public int getDepartamento() { return departamento; }

    public void setDepartamento(int departamento) { this.departamento = departamento; }

    public int getPiso() { return piso; }

    public void setPiso(int piso) { this.piso = piso; }

    public Ubicacion getUbicacion() { return ubicacion; }

    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }

    // Constructor
    public Domicilio() {}

    public Domicilio(String provincia, String localidad, int codigoPostal, String calle, int numero, int departamento, int piso, Ubicacion ubicacion) {
        this.provincia = provincia;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.calle = calle;
        this.numero = numero;
        this.departamento = departamento;
        this.piso = piso;
        this.ubicacion = ubicacion;
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
